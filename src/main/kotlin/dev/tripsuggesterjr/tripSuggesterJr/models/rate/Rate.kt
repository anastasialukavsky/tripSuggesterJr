package dev.tripsuggesterjr.tripSuggesterJr.models.rate

import dev.tripsuggesterjr.tripSuggesterJr.models.hotel.Hotel
import jakarta.persistence.*
import java.time.Month
import java.util.*


@Entity
@Table(name = "rate")
data class Rate(
    @Id
    @GeneratedValue
    var rateId: UUID = UUID.randomUUID(),

    var rate: Double = 0.0,

    @Enumerated(EnumType.STRING)
    var month: Month,

    var defaultValue: Double = 234.00,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    var hotel: Hotel? = null
)