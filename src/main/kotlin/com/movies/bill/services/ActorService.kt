package com.movies.bill.services

import com.movies.bill.MovieLogger
import com.movies.bill.dao.ActorRepository
import com.movies.bill.dto.CreateActorRequest
import com.movies.bill.exception.CustomErrorResponse
import com.movies.bill.exception.CustomException
import com.movies.bill.models.Actor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.ErrorResponse
import org.springframework.web.ErrorResponseException
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ActorService(private val actorRepository: ActorRepository) {

//    @Autowired
//    lateinit var actorRepository: ActorRepository

    private val logger = MovieLogger()

    fun getAll(): List<Actor> = actorRepository.findAll()

    fun getById(id: UUID): Optional<Actor> = actorRepository.findById(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getByName(name: String): Actor? {
        val actor = actorRepository.getByName(name)
        if (actor != null) {
            return Actor(actor.id, actor.name)
        }
        return null
    }

    @Transactional
    fun createActor(actorRequest: CreateActorRequest): Actor {
        val actorObject = getByName(actorRequest.name)
        if(actorObject!=null){
            throw IllegalArgumentException("Actor with name ${actorRequest.name} already exists")
        }
        val actorCreated = Actor(actorRequest.id, actorRequest.name)
        try {
            actorRepository.save(actorCreated)
        } catch (ex: DataIntegrityViolationException) {
            val errorCode = "DATA_INTEGRITY_VIOLATION"
            val errorMessage = "Data integrity violation occurred for Actor entity: ${ex.localizedMessage}"
            throw CustomException(CustomErrorResponse(errorCode, errorMessage))
        }

        // Return the created user object
        return actorCreated
    }

//    fun createActorOld(actor: Actor): Actor {
//        // TODO handle data integrity exception for duplicate actors. return response in readable format
//        var actor = repository.save(actor)
//        return actor
//    }

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
    //        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Actor with name $name does not exist")

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