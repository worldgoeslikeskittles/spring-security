package com.example.springsecurityold.security.tokens

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class DoctorUsernamePasswordAurhenticationToken(principal: Object, credentials: Object,authorities: MutableCollection<out GrantedAuthority>?) :
    AbstractAuthenticationToken(authorities) {
    override fun getCredentials(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any {
        TODO("Not yet implemented")
    }
}