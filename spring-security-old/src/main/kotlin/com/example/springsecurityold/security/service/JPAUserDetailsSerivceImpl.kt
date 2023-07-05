package com.example.springsecurityold.security.service

import com.example.springsecurityold.domain.repo.UserRepository
import com.example.springsecurityold.security.entity.SecurityUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JPAUserDetailsSerivceImpl(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findUserByUsername(username)
            .takeIf { it != null }
            ?.let { SecurityUser(it) }
            ?: throw UsernameNotFoundException("No such user in database!")
}