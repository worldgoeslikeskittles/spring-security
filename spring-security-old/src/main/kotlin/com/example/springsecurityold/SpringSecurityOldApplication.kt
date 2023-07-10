package com.example.springsecurityold

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSecurityOldApplication

fun main(args: Array<String>) {
    val a = runApplication<SpringSecurityOldApplication>(*args)
    val b = 1;
}