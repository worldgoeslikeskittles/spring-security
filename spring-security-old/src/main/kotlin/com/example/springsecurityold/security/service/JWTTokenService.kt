package com.example.springsecurityold.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct
import kotlin.collections.HashMap

@Service
class JWTTokenService(
    @Value("\${jwt.secret}")
    private var secret: String,
    @Value("\${jwt.expiration}")
    private val expirationInMs: Long,
    @Value("\${jwt.header}")
    private val header: String
) {

    @PostConstruct
    fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun generateToken(authentication: Authentication): String {
        val authorties = authentication.authorities.map{it.authority}
        val claims: HashMap<String, Any> = hashMapOf ("roles" to authorties)
        val issuedDate = Date();
        val expirationDate = Date(issuedDate.time + expirationInMs)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(authentication.name)
            .setIssuedAt(issuedDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getDataFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
    }

    fun getUsernameFromToken(token: String) = getDataFromToken(token).subject

    fun getRolesFromToken(token: String) = getDataFromToken(token).get("roles", List::class.java)
}