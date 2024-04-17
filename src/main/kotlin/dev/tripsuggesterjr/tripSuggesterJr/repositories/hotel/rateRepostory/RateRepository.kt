package dev.tripsuggesterjr.tripSuggesterJr.repositories.hotel.rateRepostory

import dev.tripsuggesterjr.tripSuggesterJr.models.rate.Rate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RateRepository : JpaRepository<Rate, UUID> {
}