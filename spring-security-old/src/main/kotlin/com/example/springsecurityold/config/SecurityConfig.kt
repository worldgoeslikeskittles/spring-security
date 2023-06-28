package com.example.springsecurityold.config

import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.security.filter.JWTTokenFilter
import com.example.springsecurityold.security.service.JWTTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JWTTokenProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.httpBasic()
        http.authorizeRequests()
            .antMatchers("/registration")
            .permitAll()
            .mvcMatchers("/registration/manager").hasRole(Role.ADMIN.authority)
            .mvcMatchers("/registration/doctor").hasRole(Role.ADMIN.authority)
            .antMatchers("**/current").permitAll() // проверить
            .mvcMatchers("/clients/all").hasAnyRole(Role.MANAGER.authority, Role.ADMIN.authority)
            .mvcMatchers("/clients/{id}").not().hasRole(Role.CLIENT.authority) // проверить
            .mvcMatchers("/visits/doctor/{doctorId}").hasAnyRole(Role.MANAGER.authority, Role.ADMIN.authority)
            .mvcMatchers("/employees/doctors").permitAll()
            .anyRequest().authenticated()
    // и так далее...
    }

    override fun configure(web: WebSecurity) {
        super.configure(web)
        // какие-то настройки
    }

    fun getJWTTokenFilter(jwtTokenProvider: JWTTokenProvider) = JWTTokenFilter()

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()
}