package com.example.springsecurityold.security.service

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtTokenService {

    fun logout(requet: HttpServletRequest, response: HttpServletResponse) {
        SecurityContextLogoutHandler().logout(requet, response, null)
    }
}