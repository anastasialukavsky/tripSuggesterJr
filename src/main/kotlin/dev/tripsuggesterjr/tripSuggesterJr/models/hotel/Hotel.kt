package dev.tripsuggesterjr.tripSuggesterJr.models.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.amenity.Amenity
import dev.tripsuggesterjr.tripSuggesterJr.models.location.Location
import dev.tripsuggesterjr.tripSuggesterJr.models.rate.Rate
import jakarta.persistence.*
import java.util.*

@Entity
data class Hotel(
    @Id
    @GeneratedValue
    val hotelId: UUID = UUID.randomUUID(),

    var name: String = "",

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    var location: Location,

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    var rate: Set<Rate> = HashSet(),

    var phoneNumber: String = "",

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id")
    var amenities: Set<Amenity> = HashSet()

)