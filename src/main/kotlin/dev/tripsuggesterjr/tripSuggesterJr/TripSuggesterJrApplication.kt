package dev.tripsuggesterjr.tripSuggesterJr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class TripSuggesterJrApplication

fun main(args: Array<String>) {
	runApplication<TripSuggesterJrApplication>(*args)
}

//@RestController
//class MessageController {
//	@GetMapping("/")
//	fun index() = listOf(
//		Message("1", "Hello!"),
//		Message("2", "Bonjour!"),
//		Message("3", "Privet!"),
//	)
//}

//data class Message(val id: String?, val text: String)