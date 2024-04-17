package dev.tripsuggesterjr.tripSuggesterJr.models.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.rate.Rate
import jakarta.persistence.*
import java.util.*

@Entity
data class Hotel(
    @Id
    @GeneratedValue
    val hotelId: UUID = UUID.randomUUID(),

    var name: String = "",

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "location_id")
//    var location: Location,


    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
//    @JoinColumn(name = "rate_id")
    var rates: Set<Rate>? = HashSet(),

    var phoneNumber: String = "",

//    @JoinColumn(name = "amenity_id")

//    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
//    var amenities: Set<Amenity> = HashSet()

)