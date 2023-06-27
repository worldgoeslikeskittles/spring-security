package com.example.springsecurityold.domain.repository

import com.example.springsecurityold.domain.entity.Employee
import com.example.springsecurityold.domain.enumerated.EmployeeType
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Employee, Long> {

    fun findAllByEmployeeType(employeeType: EmployeeType): Set<Employee>

    fun findAllByEmployeeTypeAndFullName(employeeType: EmployeeType, fullName: String): Set<Employee>

    fun findAllByEmployeeTypeAndSpeciality(emploeeType: EmployeeType, speciality: String): Set<Employee>

    fun findEmployeeByEmployeeTypeAndPhone(employeeType: EmployeeType, phone: String): Employee?
}