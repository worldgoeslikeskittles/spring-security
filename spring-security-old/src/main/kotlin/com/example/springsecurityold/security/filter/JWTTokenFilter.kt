package com.example.springsecurityold.security.filter

import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.security.service.JWTTokenService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTTokenFilter(
    private val jwtTokenService: JWTTokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val auth = request.getHeader("Authorization")
            if(auth != null && auth.startsWith("Bearer ")) {
                val jwt = auth.substring(7)
                val userName = jwtTokenService.getUsernameFromToken(jwt)
                val roles = jwtTokenService.getRolesFromToken(jwt)
                if (roles.contains(Role.DOCTOR.authority)){
                    val userNamePasswordToken = UsernamePasswordAuthenticationToken(userName, null, roles.map{SimpleGrantedAuthority(it as String)})
                    SecurityContextHolder.getContext().authentication = userNamePasswordToken
                }
            }
        } catch (e: AuthenticationException) {
            SecurityContextHolder.clearContext()
            response.sendError(HttpStatus.UNAUTHORIZED.value())
            throw AuthenticationException("Some troubles with JWT")
        }
        filterChain.doFilter(request, response)
    }
}