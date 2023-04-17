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

    private val logger = MovieLogger()

    fun getAll(): List<Actor> = actorRepository.findAll()

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

    fun saveIfNotPresent(id: String, name: String) {
        val actor = actorRepository.getByName(name)
        if(actor==null){
            actorRepository.save( Actor(id,name));
        }
    }

    fun getOrphanedActors():List<String>{
        return actorRepository.getOrphanedActors();
    }

    fun deleteActor(name:String){
        actorRepository.deleteActor(name);
    }
}