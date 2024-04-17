package dev.tripsuggesterjr.tripSuggesterJr.services.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.hotel.Hotel
import dev.tripsuggesterjr.tripSuggesterJr.models.rate.Rate
import dev.tripsuggesterjr.tripSuggesterJr.repositories.hotel.HotelRepository
import dev.tripsuggesterjr.tripSuggesterJr.repositories.hotel.rateRepostory.RateRepository
import dev.tripsuggesterjr.tripSuggesterJr.services.hotel.rateService.RateService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Month
import java.util.*


@Service
class HotelService {

    @Autowired
    private lateinit var rateRepo: RateRepository

    @Autowired
    lateinit var hotelRepo: HotelRepository

    @Autowired
    lateinit var rateService: RateService

    //TODO throw custom 404 exception instead
    fun getHotelById(id: UUID): Optional<Hotel> {
        return hotelRepo.findById(id)
    }

    fun getAllHotels(): List<Hotel>? {
        return hotelRepo.findAll()
    }

//    fun createHotel(hotel: Hotel): Hotel {
//                val newHotel = hotelRepo.save(hotel)
//
//
//        val newRate = Rate(
//        rate = 0.0,
//        month = Month.JANUARY,
//        defaultValue = 200.00,
//        hotel = newHotel
//        )
//
//        try {
//            rateService.createRate(newRate)
//        } catch(e: Exception) {
//            throw  e
//        }
//        return newHotel
//
////        val createdHotel = hotelRepo.save(hotel)
////
////        val newRate = Rate(
////        rate = 0.0,
////       month = Month.APRIL,
////        defaultValue = 0.0,
////        hotel = createdHotel // Associate the Rate with the saved Hotel
////        )
////        runBlocking {
////            // Execute createRate function in a coroutine scope to await its completion
////            rateService.createRate(newRate)
////        }
////        return createdHotel
//    }

    @Transactional
    fun createHotel(hotel: Hotel): Hotel {
        try {
            val createdHotel = hotelRepo.save(hotel)

            val newRate = Rate(
                rate = 0.0, // Set initial rate value
                month = Month.APRIL, // Set initial month value
                defaultValue = 0.0, // Set initial default value
                hotel = createdHotel // Associate the Rate with the saved Hotel
            )
            rateRepo.save(newRate) // Save the rate

            return createdHotel
        } catch (ex: Exception) {
            // Log or handle the exception appropriately
            throw ex
        }
    }

    fun updateHotelById(id: UUID, hotel: Hotel): Hotel {
        val existingHotel = hotelRepo.getReferenceById(id)
        existingHotel.name = hotel.name
        existingHotel.rates = hotel.rates
//        existingHotel.location = hotel.location
//        existingHotel.amenities = hotel.amenities
        existingHotel.phoneNumber = hotel.phoneNumber

        return hotelRepo.save(existingHotel)
    }

    fun deleteHotelById(id: UUID): String {
        hotelRepo.deleteById(id)
        return "deleted"
    }
}