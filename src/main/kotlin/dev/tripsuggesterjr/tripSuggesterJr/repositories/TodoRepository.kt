package dev.tripsuggesterjr.tripSuggesterJr.repositories

import dev.tripsuggesterjr.tripSuggesterJr.models.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TodoRepository : JpaRepository<Todo, Long>