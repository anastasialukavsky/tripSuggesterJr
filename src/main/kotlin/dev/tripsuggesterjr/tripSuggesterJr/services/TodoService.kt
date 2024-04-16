package dev.tripsuggesterjr.tripSuggesterJr.services

import dev.tripsuggesterjr.tripSuggesterJr.exceptions.DataNotFoundException
import dev.tripsuggesterjr.tripSuggesterJr.models.Todo
import dev.tripsuggesterjr.tripSuggesterJr.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class TodoService {

    @Autowired
    lateinit var todoRepo: TodoRepository

    fun saveTodo(todoEntity: Todo): Todo {
        return todoRepo.save(todoEntity)
    }

    fun updateTodo(todoEntity: Todo, todoId: Long): Todo {
        if (todoEntity.id == todoId) {
            return try {
                val newTodo: Todo = todoRepo.getReferenceById(todoId)
                newTodo.title = todoEntity.title
                newTodo.description = todoEntity.description
                newTodo.progress = todoEntity.progress
                todoRepo.save(newTodo)
            } catch (e: Exception) {
                throw Exception("Unable to update data with id $todoId")
            }

        }

        throw DataNotFoundException("Id used not valid")
    }

    fun retrieveAllTodos(): List<Todo>? {
        return todoRepo.findAll()
    }

    fun deleteTodoById(todoId: Long): ResponseEntity<Map<String, String>> {
        return try {
            todoRepo.deleteById(todoId)
            val dataMessage: MutableMap<String, String> = HashMap()
            dataMessage["data"] = "Property removed"
            ResponseEntity.ok(dataMessage)
        } catch (e: EmptyResultDataAccessException) {
            throw DataNotFoundException("Unable to delete item, id not found")
        } catch (e: Exception) {
            throw e
        }
    }
}