package com.example.springsecurityold.security.filter

import com.example.springsecurityold.security.service.JWTTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTTokenFilter(
    private val jwtTokenProvider: JWTTokenProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {

            jwtTokenProvider.resolveToken(request).let {
                if (it != null && jwtTokenProvider.isTokenValid(it)) {
                    SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(it)

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