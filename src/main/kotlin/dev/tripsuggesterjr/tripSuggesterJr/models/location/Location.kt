package dev.tripsuggesterjr.tripSuggesterJr.models.location

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
data class Location(
    @Id
    @GeneratedValue()
    var locationId: UUID = UUID.randomUUID(),

    var latitude: Double = 0.0,

    var longitude: Double = 0.0

)