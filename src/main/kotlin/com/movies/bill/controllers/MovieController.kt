package com.movies.bill.controllers

import com.movies.bill.dto.CreateMovieRequest
import com.movies.bill.models.Movie
import com.movies.bill.services.MovieService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
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

    @PostMapping("/updateMovie")
    fun updateMovie(@Valid @RequestBody movie:CreateMovieRequest): ResponseEntity<Void> {
        movieService.updateMovie(movie)
        return ResponseEntity.noContent().build<Void>()
    }

    @DeleteMapping("/deleteMovie")
    fun deleteMovie(@RequestParam("title") movieName:String): ResponseEntity<Void> {
        movieService.deleteMovie(movieName)
//        return ResponseEntity<Void>;
        return ResponseEntity.noContent().build<Void>()
    }
}