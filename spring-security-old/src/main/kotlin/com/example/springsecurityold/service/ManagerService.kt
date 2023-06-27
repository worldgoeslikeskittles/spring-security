package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.Employee
import com.example.springsecurityold.domain.enumerated.EmployeeType
import com.example.springsecurityold.domain.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class ManagerService(
    private val employeeRepository: EmployeeRepository
) {
    fun getActual(): Employee? {
        val mockedId = 1L

        return employeeRepository.findById(mockedId).orElse(null)
    }

    fun getById(id: Long): Employee? = employeeRepository.findById(id).orElse(null)

    fun getManagers(fullName: String? = null, phone: String? = null): Set<Employee> = if (!fullName.isNullOrBlank()) {
        employeeRepository.findAllByEmployeeTypeAndFullName(EmployeeType.MANAGER, fullName)
    } else if (!phone.isNullOrBlank()) {
        setOfNotNull(employeeRepository.findEmployeeByEmployeeTypeAndPhone(EmployeeType.MANAGER, phone))
    } else {
        employeeRepository.findAllByEmployeeType(EmployeeType.MANAGER)
    }
}