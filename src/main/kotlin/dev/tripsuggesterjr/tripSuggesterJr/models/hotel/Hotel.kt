package dev.tripsuggesterjr.tripSuggesterJr.models.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.location.Location
import dev.tripsuggesterjr.tripSuggesterJr.models.rate.Rate
import jakarta.persistence.*
import java.util.*

@Entity
data class Hotel(
    @Id
    @GeneratedValue
    val hotelId: UUID = UUID.randomUUID(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    var location: Location,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    var rate: Rate,

    var name: String = "",

    var phoneNumber: String = ""

)