package dev.tripsuggesterjr.tripSuggesterJr.controllers

import dev.tripsuggesterjr.tripSuggesterJr.models.Todo
import dev.tripsuggesterjr.tripSuggesterJr.services.TodoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/api")
class TodoController {


    @Autowired
    lateinit var todoService: TodoService

    @PostMapping("/todo")
    fun saveTodo(@Valid @RequestBody todo: Todo): Todo {
        return todoService.saveTodo(todo)
    }

    @GetMapping("/todo")
    fun getAllTodos(): List<Todo>? {
        return todoService.retrieveAllTodos()
    }

    @PutMapping("/todo/{id}")
    fun updateTodo(
        @PathVariable("id") todoId: Long,
        @Valid @RequestBody todo: Todo
    ): Todo {
        return todoService.updateTodo(todo, todoId)
    }


    @DeleteMapping("/todo/{id}")
    fun deleteTodoById(@PathVariable("id") todoId: Long): ResponseEntity<Map<String, String>> {
        return todoService.deleteTodoById(todoId)

    }
}