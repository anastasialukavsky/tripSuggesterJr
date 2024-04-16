package dev.tripsuggesterjr.tripSuggesterJr.models.rate

import jakarta.persistence.*
import java.time.Month
import java.util.*


@Entity
data class Rate(
    @Id
    @GeneratedValue
    var rateId: UUID = UUID.randomUUID(),

    var rate: Double = 0.0,

    @Enumerated(EnumType.STRING)
    var month: Month,

    var defaultValue: Double = 234.00
)