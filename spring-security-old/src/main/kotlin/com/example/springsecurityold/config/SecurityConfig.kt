package com.example.springsecurityold.config

import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.security.providers.UsernamePasswordAuthenticationProvider
import com.example.springsecurityold.security.service.JPAUserDetailsSerivceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig(
    private val jpaUserDetailsSerivceImpl: JPAUserDetailsSerivceImpl
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http.httpBasic()
        http.formLogin()
        http.authorizeHttpRequests()
            .antMatchers("/registration").permitAll()
            .mvcMatchers("/registration/manager").hasRole(Role.ADMIN.name)
            .mvcMatchers("/registration/doctor").hasRole(Role.ADMIN.name)
            .antMatchers("**/current").permitAll()
            .mvcMatchers("/clients/all").hasAnyRole(Role.MANAGER.name, Role.ADMIN.name)
            .mvcMatchers("/clients/{id}")
            .hasAnyRole(Role.MANAGER.name, Role.ADMIN.name, Role.DOCTOR.name)
            .mvcMatchers("/visits/doctor/{doctorId}").hasAnyRole(Role.MANAGER.name, Role.ADMIN.name)
            .mvcMatchers("/employees/doctors").permitAll()
            .mvcMatchers("dkskskdk").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()

        // и так далее...
    }

    override fun configure(web: WebSecurity) {
        super.configure(web)
        // какие-то настройки
    }

//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(usernamePasswordAuthenticationProvider())
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

//    @Bean
//    fun usernamePasswordAuthenticationProvider() =
//        UsernamePasswordAuthenticationProvider(jpaUserDetailsSerivceImpl, passwordEncoder())
}