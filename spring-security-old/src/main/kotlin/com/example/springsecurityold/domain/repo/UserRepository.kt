package com.example.springsecurityold.domain.repo

import com.example.springsecurityold.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>  {

    fun findUserByUsername(username: String) : User?
}