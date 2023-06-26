package com.example.springsecurityold.domain.entity

import javax.persistence.*

@Entity
@Table(name = "authorities")
class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null
    @Column(name = "name", nullable = false)
    val name: String? = null
}