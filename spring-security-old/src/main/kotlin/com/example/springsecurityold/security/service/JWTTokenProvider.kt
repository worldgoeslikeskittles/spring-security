package com.example.springsecurityold.security.service

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*
import javax.annotation.PostConstruct
import javax.naming.AuthenticationException
import javax.servlet.http.HttpServletRequest

@Service
class JWTTokenProvider(
    @Value("\${jwt.secret}")
    private var secret: String,
    @Value("\${jwt.expiration}")
    private val expirationInMs: Long,
    @Value("\${jwt.header}")
    private val header: String,
    private val userDetailsService: UserDetailsService
) {

    @PostConstruct
    fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(login: String, password: String): String{
        val claims = Jwts.claims().setSubject(login)
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + expirationInMs))
            .signWith(SignatureAlgorithm.ES256, secret)
            .compact()
    }

    fun getAuthentication(token: String) = userDetailsService.loadUserByUsername(getUsername(token))
        .let { UsernamePasswordAuthenticationToken(it, it.authorities) }


    fun resolveToken(request: HttpServletRequest): String? = request.getHeader(header)

    fun isTokenValid(token: String): Boolean = try {
        Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
            .expiration
            .before(Date())
    } catch (e: Exception) {
        when (e) {
            is JwtException, is IllegalArgumentException -> {
                throw AuthenticationException("Some troubles with JWT")
            }
            else -> throw e
        }
    }

    private fun getUsername(token: String) =
        Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
            .subject
}