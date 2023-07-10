package com.example.springsecurityold.security.providers

import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.security.service.JPAUserDetailsSerivceImpl
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

class DoctorUsernamePaswordAuthenticationProvider(
    private val jpaUserDetailsService: JPAUserDetailsSerivceImpl,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val name = authentication.name
        val password = authentication.credentials.toString()

        val user: UserDetails = jpaUserDetailsService.loadUserByUsername(name)
        if (passwordEncoder.matches(password, user.password)
            && user.authorities.first().equals(Role.DOCTOR)) {
            return UsernamePasswordAuthenticationToken(name, password, user.authorities)
        }
        throw BadCredentialsException("Unable to authenticate by username and password");
    }

    override fun supports(authentication: Class<*>): Boolean =
        authentication == UsernamePasswordAuthenticationToken::class.java
}