package dev.tripsuggesterjr.tripSuggesterJr.config

import dev.tripsuggesterjr.tripSuggesterJr.exceptions.DataNotFoundException
import dev.tripsuggesterjr.tripSuggesterJr.models.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class CustomControllerException {

    @ExceptionHandler(Exception::class)
    fun handleExceptions(e: Exception): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR

        return ResponseEntity<ErrorResponse>(
            ErrorResponse(status, e.message),
            status
        )
    }

    @ExceptionHandler(DataNotFoundException::class)
    fun handleCustomNotFoundException(
        e: Exception
    ): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.NOT_FOUND
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(
                status,
                e.message
            ),
            status
        )
    }
}