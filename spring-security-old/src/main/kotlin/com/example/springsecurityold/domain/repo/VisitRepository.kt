package com.example.springsecurityold.domain.repo

import com.example.springsecurityold.domain.entity.Visit
import org.springframework.data.jpa.repository.JpaRepository

interface VisitRepository : JpaRepository<Visit, Long> {
    fun findAllByDoctorIdOrderByVisitTimeDesc(doctorId: Long): List<Visit>
}