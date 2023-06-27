package com.example.springsecurityold.security.authentication

import com.example.springsecurityold.domain.entity.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

data class UserAuthentication(
    private val user: User
): Authentication {
    override fun getName(): String = user.login

    override fun getAuthorities(): Collection<GrantedAuthority> = user.role.permissions.plus(user.role)

    override fun getCredentials(): Any = user.password

    override fun getDetails(): Any = user

    override fun getPrincipal(): Any = user

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}