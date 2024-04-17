package dev.tripsuggesterjr.tripSuggesterJr.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.util.*

data class HotelDTO(
    val id: UUID,

    @field:NotBlank(message = "Hotel name is required")
    var name: String,

    @field:NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})\$",
        message = "Invalid phone number. Must be in the format (XXX) XXX-XXXX or XXX-XXX-XXXX"
    )
    var phoneNumber: String

)