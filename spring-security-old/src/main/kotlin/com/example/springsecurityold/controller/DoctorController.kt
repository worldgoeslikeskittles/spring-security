package com.example.springsecurityold.controller

import com.example.springsecurityold.service.DoctorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/doctor")
class DoctorController(
    private val doctorService: DoctorService
) {

    @GetMapping("/actual")
    fun getActualDoctor() = doctorService.getActual()

    @GetMapping("/{id}")
    fun getDoctorById(
        @PathVariable("id") id: Long
    ) = doctorService.getById(id)

    @GetMapping
    fun getDoctors(
        @RequestParam("full_name", required = false) fullName: String?,
        @RequestParam("speciality", required = false) speciality: String?
    ) = doctorService.getDoctors(fullName, speciality)
}