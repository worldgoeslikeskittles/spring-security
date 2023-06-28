package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.Employee
import com.example.springsecurityold.domain.enumerated.EmployeeType
import com.example.springsecurityold.domain.repo.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {

    fun saveEmloyee(employee: Employee) = employeeRepository.save(employee)

    fun getAllEmployeesByType(employeeType: EmployeeType) = employeeRepository.findAllByEmployeeType(employeeType)
}