package com.example.springsecurityold.config

import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.security.filter.JWTTokenFilter
import com.example.springsecurityold.security.providers.DoctorUsernamePaswordAuthenticationProvider
import com.example.springsecurityold.security.service.JPAUserDetailsSerivceImpl
import com.example.springsecurityold.security.service.JWTTokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Order(1)
@Configuration
class SessionlessSecurityConfig(
    private val jpaUserDetailsSerivceImpl: JPAUserDetailsSerivceImpl,
    private val jwtTokenService: JWTTokenService
) : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
            .httpBasic().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .csrf().disable()
            .addFilterAt(JWTTokenFilter(jwtTokenService), BasicAuthenticationFilter::class.java)
        .authorizeRequests()
            .antMatchers("/registration").permitAll()
            .antMatchers("**/current").permitAll()
            .mvcMatchers("/clients/all").hasRole(Role.MANAGER.name)
            .mvcMatchers("/clients/{id}").hasAnyRole(Role.MANAGER.name, Role.DOCTOR.name)
            .mvcMatchers("/visits/doctor/{doctorId: regex}").hasAnyRole(Role.MANAGER.name)
            .mvcMatchers("/employees/doctors").permitAll()
            .mvcMatchers("/auth/jwt/authenticate").permitAll()

            .mvcMatchers("/clients").hasAnyRole(Role.MANAGER.name, Role.DOCTOR.name)
            .antMatchers("/clients").hasAnyRole(Role.MANAGER.name, Role.DOCTOR.name)


            .anyRequest().denyAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.parentAuthenticationManager(authenticationManagerBean())
            .authenticationProvider(doctorUsernamePasswordAuthenticationProvider())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun doctorUsernamePasswordAuthenticationProvider() =
        DoctorUsernamePaswordAuthenticationProvider(jpaUserDetailsSerivceImpl, passwordEncoder())

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

}