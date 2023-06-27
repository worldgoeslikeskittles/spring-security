package com.example.springsecurityold.service

import com.example.springsecurityold.domain.entity.Employee
import com.example.springsecurityold.domain.enumerated.EmployeeType
import com.example.springsecurityold.domain.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class DoctorService(
    private val employeeRepository: EmployeeRepository
) {
    fun getActual(): Employee? {
        val mockedId = 1L
        return employeeRepository.findById(mockedId).orElse(null)
    }

    fun getById(id: Long): Employee? = employeeRepository.findById(id).orElse(null)

    fun getDoctors(fullName: String? = null, speciality: String? = null): Set<Employee> =
        if (!fullName.isNullOrBlank()) {
            employeeRepository.findAllByEmployeeTypeAndFullName(EmployeeType.DOCTOR, fullName)
        } else if (!speciality.isNullOrBlank()) {
            employeeRepository.findAllByEmployeeTypeAndSpeciality(EmployeeType.DOCTOR, speciality)
        } else {
            employeeRepository.findAllByEmployeeType(EmployeeType.DOCTOR)
        }
}