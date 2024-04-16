package dev.tripsuggesterjr.tripSuggesterJr.models.rate

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.Month
import java.util.*


@Entity
data class Rate(
    @Id
    @GeneratedValue
    var rateId: UUID = UUID.randomUUID(),

    var rate: Double = 0.0,

    var month: Month,

    var defaultValue: Double = 234.00
)