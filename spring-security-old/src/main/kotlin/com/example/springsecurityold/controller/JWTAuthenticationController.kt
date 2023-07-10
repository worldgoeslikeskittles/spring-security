package com.example.springsecurityold.controller

import com.example.springsecurityold.dto.JWTAuthenticationRequestDto
import com.example.springsecurityold.dto.JWTResponseDto
import com.example.springsecurityold.security.filter.JWTTokenFilter
import com.example.springsecurityold.security.service.JWTTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/jwt")
class JWTAuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenService: JWTTokenService
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody dto: JWTAuthenticationRequestDto): JWTResponseDto {
        val auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(dto.username, dto.password))
        val token = jwtTokenService.generateToken(auth)
        return JWTResponseDto(token)
    }

    @PostMapping("/logout")
    fun logout(){

    }
}