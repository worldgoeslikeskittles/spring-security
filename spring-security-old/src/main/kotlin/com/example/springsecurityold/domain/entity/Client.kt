package com.example.springsecurityold.domain.entity

import javax.persistence.*


@Entity
@Table(name = "clients")
class Client (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    @Column(name = "phone", nullable = false)
    var phone: String,
    @Column(name = "full_name", nullable = false)
    var fullName: String,
    @Column(name = "user_id", nullable = false)
    var userId: Long
)