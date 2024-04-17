package dev.tripsuggesterjr.tripSuggesterJr.services.hotel.rateService

import dev.tripsuggesterjr.tripSuggesterJr.repositories.hotel.rateRepostory.RateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class RateService {
    @Autowired
    lateinit var rateRepo: RateRepository
//
//    fun createRate(rate: Rate): Rate {
//        return rateRepo.save(rate)
//    }

}