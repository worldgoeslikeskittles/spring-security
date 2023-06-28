package com.example.springsecurityold.domain.repo

import com.example.springsecurityold.domain.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long> {
    fun getClientById(id: Long): Client?

    fun getClientByUserId(userId: Long): Client?
}