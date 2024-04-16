package dev.tripsuggesterjr.tripSuggesterJr.services.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.hotel.Hotel
import dev.tripsuggesterjr.tripSuggesterJr.repositories.hotel.HotelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class HotelService {

    @Autowired
    lateinit var hotelRepo: HotelRepository

    //TODO throw custom 404 exception instead
    fun getHotelById(id: UUID): Optional<Hotel> {
        return hotelRepo.findById(id)
    }

    fun getAllHotels(): List<Hotel>? {
        return hotelRepo.findAll()
    }

    fun createHotel(hotel: Hotel): Hotel {
        return hotelRepo.save(hotel)
    }

    fun updateHotelById(id: UUID, hotel: Hotel): Hotel {
        val existingHotel = hotelRepo.getReferenceById(id)
        existingHotel.name = hotel.name
        existingHotel.rate = hotel.rate
        existingHotel.location = hotel.location
        existingHotel.amenities = hotel.amenities
        existingHotel.phoneNumber = hotel.phoneNumber

        return hotelRepo.save(existingHotel)
    }

    fun deleteHotelById(id: UUID): String {
        hotelRepo.deleteById(id)
        return "deleted"
    }
}