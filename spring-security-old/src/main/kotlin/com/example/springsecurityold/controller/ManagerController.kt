package com.example.springsecurityold.controller

import com.example.springsecurityold.service.ManagerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/manager")
class ManagerController(
    private val managerService: ManagerService
) {

    @GetMapping("/actual")
    fun getActualManager() = managerService.getActual()

    @GetMapping("/{id}")
    fun getManagerById(
        @PathVariable("id") id: Long
    ) = managerService.getById(id)

    @GetMapping
    fun getManagers(
        @RequestParam("full_name", required = false) fullName: String? = null,
        @RequestParam("phone", required = false) phone: String? = null
    ) = managerService.getManagers(fullName, phone)
}