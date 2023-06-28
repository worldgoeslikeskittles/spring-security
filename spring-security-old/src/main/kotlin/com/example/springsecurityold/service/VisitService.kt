package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.Visit
import com.example.springsecurityold.dto.VisitDto
import com.example.springsecurityold.domain.repo.VisitRepository
import org.springframework.stereotype.Service

@Service
class VisitService(
    private val visitRepository: VisitRepository
) {
    fun createVisit(dto: VisitDto) = visitRepository.save(
        Visit(
            clientId = dto.clientId,
            doctorId = dto.doctorId,
            visitNotes = dto.visitNotes,
            visitTime = dto.visitTime
        )
    )

    fun getAllVisitsForDoctor(doctorId: Long) = visitRepository.findAllByDoctorIdOrderByVisitTimeDesc(doctorId)
}