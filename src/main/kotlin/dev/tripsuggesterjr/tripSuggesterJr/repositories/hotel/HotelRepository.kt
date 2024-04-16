package dev.tripsuggesterjr.tripSuggesterJr.repositories.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.hotel.Hotel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HotelRepository : JpaRepository<Hotel, UUID>