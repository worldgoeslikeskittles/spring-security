package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.Client
import com.example.springsecurityold.domain.entity.Employee
import com.example.springsecurityold.domain.entity.User
import com.example.springsecurityold.domain.enumerated.EmployeeType
import com.example.springsecurityold.domain.enumerated.Role
import com.example.springsecurityold.dto.ClientRegistrationDto
import com.example.springsecurityold.dto.EmployeeRegistrationDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val userService: UserService,
    private val clientService: ClientService,
    private val employeeService: EmployeeService
) {

    @Transactional
    fun registerClient(dto: ClientRegistrationDto) =
        userService.saveUser(User(username = dto.login, password = dto.password, role = Role.CLIENT))
            .let { clientService.saveClient(Client(phone = dto.phone, fullName = dto.fullName, userId = it.id!!)) }

    @Transactional
    fun registerManager(dto: EmployeeRegistrationDto) =
        userService.saveUser(
            User(
                username = dto.login,
                password =
                dto.password,
                role = if (dto.isAdmin) {
                    Role.ADMIN
                } else Role.MANAGER
            )
        ).let {
            employeeService.saveEmloyee(
                Employee(
                    phone = dto.phone,
                    workHours = dto.workingHours,
                    employeeType = if (dto.isAdmin) {
                        EmployeeType.ADMIN
                    } else {
                        EmployeeType.MANAGER
                    },
                    userId = it.id!!,
                    speciality = null,
                    fullName = dto.fullName
                )
            )
        }

    fun registerDoctor(dto: EmployeeRegistrationDto) =
        userService.saveUser(
            User(
                username = dto.login,
                password =
                dto.password,
                role = if (dto.isAdmin) {
                    Role.ADMIN
                } else Role.DOCTOR
            )
        ).let {
            employeeService.saveEmloyee(
                Employee(
                    phone = dto.phone,
                    workHours = dto.workingHours,
                    employeeType = if (dto.isAdmin) {
                        EmployeeType.ADMIN
                    } else {
                        EmployeeType.DOCTOR
                    },
                    userId = it.id!!,
                    speciality = null,
                    fullName = dto.fullName
                )
            )
        }
}