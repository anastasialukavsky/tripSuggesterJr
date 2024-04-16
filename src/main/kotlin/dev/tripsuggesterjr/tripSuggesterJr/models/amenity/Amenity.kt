package dev.tripsuggesterjr.tripSuggesterJr.models.amenity

import dev.tripsuggesterjr.tripSuggesterJr.models.hotel.Hotel
import jakarta.persistence.*
import java.util.*


@Entity
data class Amenity(
    @Id
    @GeneratedValue
    val amenityId: UUID = UUID.randomUUID(),

    var amenityName: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    var hotel: Hotel? = null
)