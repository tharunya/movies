package com.movies.bill.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.movies.bill.dao.ActorRepository
import com.movies.bill.models.Actor
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ActorService(val repository: ActorRepository) {
    fun getAll(): List<Actor> = repository.findAll()

    fun getById(id: Long): Actor = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun createActor(actor: Actor): Actor = repository.save(actor)

    /**
     * add kotlin docs
     */
    fun deleteActorById(id: Long): Boolean {
        return try {
            if (repository.existsById(id)) {
                repository.deleteById(id)
                true // Deletion successful
            } else {
                false // Entity with specified ID does not exist
            }
        } catch (ex: EmptyResultDataAccessException) {
            false // Entity with specified ID does not exist
        }
    }

    fun updateActor(id: Long, actor: Actor): Actor {
        return if (repository.existsById(id)) {
            repository.save(actor)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    // TODO REMOVE
    fun convertListToJson(actors: List<Actor>): String {
        // Create an instance of ObjectMapper
        val objectMapper = ObjectMapper()

        // Convert the list of objects to JSON string
        val jsonString = objectMapper.writeValueAsString(actors)
        println("$jsonString PRINTING RESPONSE")
        return jsonString
    }
}