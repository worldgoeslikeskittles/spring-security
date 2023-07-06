package com.example.springsecurityold.domain.enumerated

import org.springframework.security.core.GrantedAuthority

enum class Role(private val authority: String): GrantedAuthority {
    CLIENT("ROLE_CLIENT"),
    DOCTOR("ROLE_DOCTOR"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    override fun getAuthority(): String {
        return this.authority
    }
}