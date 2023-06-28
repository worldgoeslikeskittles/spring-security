package com.example.springsecurityold.controller

import com.example.springsecurityold.dto.VisitDto
import com.example.springsecurityold.service.VisitService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/visits")
class VisitController(
    private val visitService: VisitService
) {
    @PostMapping("/create")
    fun createVisit(dto: VisitDto) = visitService.createVisit(dto)

    @GetMapping("/doctor/{doctorId}")
    fun getVisitsForDoctor(@PathVariable("doctorId") doctorId: Long) = visitService.getAllVisitsForDoctor(doctorId)
}