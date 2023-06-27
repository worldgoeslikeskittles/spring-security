package com.example.springsecurityold.security.authentication

import com.example.springsecurityold.domain.entity.User
import com.example.springsecurityold.domain.enumerated.Role
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class UsernamePasswordAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val name = authentication?.name
        val password = authentication?.credentials as? String

        // тут будут энкодер пароля и сохранение пользователя в базу
        val mockedUser = User(
            login = "",
            password = "",
            role = Role.MANAGER
        )

        return UserAuthentication(mockedUser)
    }

    override fun supports(authentication: Class<*>?): Boolean =
        authentication == UsernamePasswordAuthenticationToken::class.java
}