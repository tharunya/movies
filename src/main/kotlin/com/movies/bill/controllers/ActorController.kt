package com.movies.bill.controllers

import com.movies.bill.models.Actor
import com.movies.bill.services.ActorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("actors")
class ActorController {

    @Autowired
    private lateinit var actorService: ActorService

    // Get all actors
    @GetMapping("/allActors",  produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllActors(): ResponseEntity<List<Actor>> = ResponseEntity.ok().body(actorService.getAll());

    // Get an actor by ID
    @GetMapping("/{id}")
    fun getActorById(@PathVariable("id") id: Long): ResponseEntity<Actor> {
        val actor = actorService.getById(id)
        return if (actor != null) {
            ResponseEntity.ok(actor)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Create a new actor
    @PostMapping
    fun createActor(@RequestBody actor: Actor): ResponseEntity<Actor> {
        val createdActor = actorService.createActor(actor)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActor)
    }

    // Update an existing actor
    @PutMapping("/{id}")
    fun updateActor(@PathVariable("id") id: Long, @RequestBody actor: Actor): ResponseEntity<Actor> {
        val updatedActor = actorService.updateActor(id, actor)
        return if (updatedActor != null) {
            ResponseEntity.ok(updatedActor)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete an actor by ID
    @DeleteMapping("/{id}")
    fun deleteActorById(@PathVariable("id") id: Long): ResponseEntity<Void> {
        val result = actorService.deleteActorById(id)
        return if (result) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}