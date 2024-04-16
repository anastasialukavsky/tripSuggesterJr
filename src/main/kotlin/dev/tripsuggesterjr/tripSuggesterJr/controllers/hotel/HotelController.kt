package dev.tripsuggesterjr.tripSuggesterJr.controllers.hotel

import dev.tripsuggesterjr.tripSuggesterJr.models.hotel.Hotel
import dev.tripsuggesterjr.tripSuggesterJr.services.hotel.HotelService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/hotels")
class HotelController {

    @Autowired
    lateinit var hotelService: HotelService

    @GetMapping("/hotels")
    fun getAllHotels(): List<Hotel>? {
        return hotelService.getAllHotels()
    }

    @GetMapping("/hotels/{id}")
    fun getHotelById(
        @PathVariable("id") id: UUID,
    ): Optional<Hotel> {
        return hotelService.getHotelById(id)
    }

    @PostMapping("/hotels")
    fun createHotel(@Valid @RequestBody hotel: Hotel): Hotel {
        return hotelService.createHotel(hotel)
    }

    @PutMapping("/hotels/{id}")
    fun updateHotelById(
        @PathVariable("id") id: UUID,
        @Valid @RequestBody hotel: Hotel
    ): Hotel {
        return hotelService.updateHotelById(id, hotel)
    }

    @DeleteMapping("/hotels/{id}")
    fun deleteHotelById(@PathVariable("id") id: UUID): String {
        return hotelService.deleteHotelById(id)
    }
}