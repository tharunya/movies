package com.movies.bill.controllers

import com.movies.bill.dto.CreateActorRequest
import com.movies.bill.models.Actor
import com.movies.bill.services.ActorService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("actors")
class ActorController {

    @Autowired
    private lateinit var actorService: ActorService

    // Get all actors
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllActors(): ResponseEntity<List<Actor>> = ResponseEntity.ok().body(actorService.getAll());

    // Get an actor by ID
//    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
//    fun getActorById(@PathVariable("id") id: UUID): ResponseEntity<Optional<Actor>> {
//        // TODO get by id uuid
//        val actor = actorService.getById(id)
//        return ResponseEntity.ok(actor)
//    }

//    @GetMapping("/byname")
//    fun getActorByName(@RequestParam("name") name: String): ResponseEntity<Actor> {
////        val actor: Array<Actor> = actorService.getByName(name)
//        val actor: String = actorService.getActorByNameString(name)
//        return ResponseEntity.ok(actor);
//    }

    @GetMapping("/byname")
    fun getActorByName(@RequestParam("name") name: String): ResponseEntity<Actor> {
        val actor = actorService.getByName(name)
        return ResponseEntity.ok(actor)
    }

    // Create a new actor
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createActor(@Valid @RequestBody actor: CreateActorRequest): ResponseEntity<Actor> {
        val createdActor = actorService.createActor(actor)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActor)
    }

    // Update an existing actor
//    @PutMapping("/{id}")
//    fun updateActor(@PathVariable("id") id: Long, @RequestBody actor: Actor): ResponseEntity<Actor> {
//        val updatedActor = actorService.updateActor(id, actor)
//        return if (updatedActor != null) {
//            ResponseEntity.ok(updatedActor)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }

//    // Delete an actor by ID
//    @DeleteMapping("/{id}")
//    fun deleteActorById(@PathVariable("id") id: Long): ResponseEntity<Void> {
//        val result = actorService.deleteActorById(id)
//        return if (result) {
//            ResponseEntity.noContent().build()
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
}