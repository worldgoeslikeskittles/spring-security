package com.example.springsecurityold.security.entity

import com.example.springsecurityold.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser( private val user: User) : UserDetails {

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean =  true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}