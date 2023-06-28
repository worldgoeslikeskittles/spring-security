package com.example.springsecurityold.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/jwt")
class JWTAuthenticationController {

    @PostMapping("/authenticate")
    fun authenticate() {

    }

    @PostMapping("/logout")
    fun logout(){

    }
}