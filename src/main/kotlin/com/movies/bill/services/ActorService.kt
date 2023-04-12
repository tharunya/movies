package com.movies.bill.services

import com.movies.bill.MovieLogger
import com.movies.bill.dao.ActorRepository
import com.movies.bill.dto.CreateActorRequest
import com.movies.bill.models.Actor
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ActorService(val repository: ActorRepository) {
    private val logger = MovieLogger()
    // TODO check if starter and web both are needed
    fun getAll(): List<Actor> = repository.findAll()

    fun getById(id: UUID): Optional<Actor> = repository.findById(id)

//    fun getById(id: UUID): Actor = repository.findById(id) ?:
//    throw ResponseStatusException(HttpStatus.NOT_FOUND)

//    fun getByName(name: String): Optional<Actor> {
//        try {
//            return repository.getByName(name)
//        } catch (ex: EmptyResultDataAccessException) {
//            ex.message?.let { logger.error(it, ex) };
//        }
//        return Optional.empty();
//    }

    fun getByName(name: String): Actor {
        var actor = repository.getByName(name)

        return Actor(actor.id, actor.name)
    }
    fun createActor(actor: Actor): Actor {
        // TODO handle data integrity exception for duplicate actors. return response in readable format
        var actor = repository.save(actor)
        return actor
    }

    /**
     * add kotlin docs
     */
//    fun deleteActorById(id: UUID): Boolean {
//        return try {
//            if (repository.existsById(id)) {
//                repository.deleteById(id)
//                true // Deletion successful
//            } else {
//                false // Entity with specified ID does not exist
//            }
//        } catch (ex: EmptyResultDataAccessException) {
//            false // Entity with specified ID does not exist
//        }
//    }
//
//    fun updateActor(id: String, actor: Actor): Actor {
//        return if (repository.existsById(id)) {
//            repository.save(actor)
//        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
//    }

    // TODO REMOVE
//    fun convertListToJson(actors: List<Actor>): String {
//        // Create an instance of ObjectMapper
//        val objectMapper = ObjectMapper()
//
//        // Convert the list of objects to JSON string
//        val jsonString = objectMapper.writeValueAsString(actors)
//        println("$jsonString PRINTING RESPONSE")
//        return jsonString
//    }
}