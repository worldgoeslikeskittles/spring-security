package com.example.springsecurityold.config

import com.example.springsecurityold.security.providers.UsernamePasswordAuthenticationProvider
import com.example.springsecurityold.security.service.JPAUserDetailsSerivceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Order(2)
@Configuration
class SessionfullSecurityConfig(
    private val jpaUserDetailsSerivceImpl: JPAUserDetailsSerivceImpl,
    private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.httpBasic().and()
            .formLogin().and()
            .sessionManagement().disable()
            .authorizeRequests { requests ->
                requests.anyRequest().authenticated()
            }
        http.authenticationProvider(usernamePasswordAuthenticationProvider())
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider())
    }


    @Bean
    fun usernamePasswordAuthenticationProvider() =
        UsernamePasswordAuthenticationProvider(jpaUserDetailsSerivceImpl, passwordEncoder)
}

