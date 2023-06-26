package com.example.springsecurityold.domain.entity

import com.example.springsecurityold.domain.enumerated.Role
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    @Column(name = "login", nullable = false)
    var login: String,
    @Column(name = "password", nullable = false)
    var password: String,
    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var role: Role
)