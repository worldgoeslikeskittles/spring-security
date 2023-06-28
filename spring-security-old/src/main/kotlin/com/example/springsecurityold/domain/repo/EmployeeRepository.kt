package com.example.springsecurityold.domain.repo

import com.example.springsecurityold.domain.entity.Employee
import com.example.springsecurityold.domain.enumerated.EmployeeType
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long> {

    fun findAllByEmployeeType(employeeType: EmployeeType): List<Employee>
}