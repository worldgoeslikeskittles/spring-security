package com.example.springsecurityold.domain.enumerated

import org.springframework.security.core.GrantedAuthority

enum class Permission : GrantedAuthority{
    SEE_ALL_CLIENTS;

    override fun getAuthority(): String {
       return this.name
    }
}