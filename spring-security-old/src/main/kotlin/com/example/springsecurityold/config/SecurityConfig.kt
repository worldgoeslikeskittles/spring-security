package com.example.springsecurityold.config

import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.security.authentication.UsernamePasswordAuthenticationProvider
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfig(
    private val authenticationProvider: UsernamePasswordAuthenticationProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/registration").permitAll()
            .mvcMatchers(HttpMethod.POST, "/registration/manager")
            .hasRole(Role.MANAGER.name)
            .anyRequest().authenticated()
            .and()
            .httpBasic()

    }

    override fun configure(web: WebSecurity) {
        super.configure(web)
        // какие-то настройки
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider)
    }
}