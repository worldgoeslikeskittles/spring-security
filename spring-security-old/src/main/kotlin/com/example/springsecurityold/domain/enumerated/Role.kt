package com.example.springsecurityold.domain.enumerated

import org.springframework.security.core.GrantedAuthority

enum class Role(val permissions: List<Permission>): GrantedAuthority {
    MANAGER(listOf(Permission.SEE_ALL_CLIENTS));

    override fun getAuthority(): String {
        return "ROLE_" + this.name
    }
}