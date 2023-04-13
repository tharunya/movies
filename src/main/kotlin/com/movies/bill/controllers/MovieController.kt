package com.movies.bill.controllers

import com.movies.bill.dto.CreateMovieRequest
import com.movies.bill.models.Movie
import com.movies.bill.services.MovieService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("movies")
class MovieController {

    @Autowired
    private lateinit var movieService: MovieService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMovies(): ResponseEntity<List<Movie>> = ResponseEntity.ok().body(movieService.getAll());

    // Get a movie by ID
//    @GetMapping("/{id}")
//    fun getMovieById(@PathVariable("id") id: Long): ResponseEntity<Optional<Movie>> {
//        val movie = movieService.getById(id)
//        return ResponseEntity.ok(movie)
//    }

    @GetMapping("/byTitle")
    fun getMovieByName(@RequestParam("title") title: String): ResponseEntity<CreateMovieRequest> {
        val movie = movieService.getMovieByName(title)
        return ResponseEntity.ok(movie)
    }


    // Create a new movie
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createMovie(@Valid @RequestBody movie: CreateMovieRequest): ResponseEntity<Movie> {
        val createdMovie = movieService.createMovie(movie)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie)
    }
}