package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.User
import com.example.springsecurityold.domain.repo.UserRepository
import com.example.springsecurityold.dto.ClientRegistrationDto
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findUserByUsername(username: String) = userRepository.findUserByUsername(username) ?: throw NotFoundException()

    fun saveUser(user: User) = userRepository.save(user)
}