package com.example.springsecurityold.dto

import java.time.LocalDateTime
import javax.persistence.Column

data class VisitDto (
    var clientId: Long,
    var doctorId: Long,
    var visitNotes: String? = null,
    var visitTime: LocalDateTime
)