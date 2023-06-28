package com.example.springsecurityold.domain.enumerated

import org.springframework.security.core.GrantedAuthority

enum class Role(): GrantedAuthority {
    CLIENT,
    DOCTOR,
    MANAGER,
    ADMIN;

    override fun getAuthority(): String {
        return this.name
    }
}