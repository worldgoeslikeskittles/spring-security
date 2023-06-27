package com.example.springsecurityold.config

import com.example.springsecurityold.domain.enumerated.Role
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http.authorizeRequests()
            .antMatchers("/registration")
            .permitAll()
            .mvcMatchers(HttpMethod.POST, "/registration/manager")
            .hasRole("MANAGER")
            .antMatchers("/")
            .hasAuthority("ROLE_MANAGER")
    }

    override fun configure(web: WebSecurity) {
        super.configure(web)
        // какие-то настройки
    }
}