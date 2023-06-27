package com.example.springsecurityold.security.service

import com.example.springsecurityold.domain.repo.UserRepository
import com.example.springsecurityold.security.entity.SecurityUser
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service

@Service
class JPAUserDetailsManagerImpl(
    private val userRepository: UserRepository
) : UserDetailsManager {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findUserByUsername(username)
            .takeIf { it != null }
            ?.let { SecurityUser(it) }
            ?: throw UsernameNotFoundException("No such user in database!")

    override fun createUser(user: UserDetails) {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserDetails) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(username: String) {
        TODO("Not yet implemented")
    }

    override fun changePassword(oldPassword: String, newPassword: String) {
        TODO("Not yet implemented")
    }

    override fun userExists(username: String): Boolean {
        TODO("Not yet implemented")
    }
}