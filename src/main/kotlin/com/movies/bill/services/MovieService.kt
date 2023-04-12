package com.movies.bill.services

import com.movies.bill.MovieLogger
import com.movies.bill.dao.MovieActorRepository
import com.movies.bill.dao.MovieRepository
import com.movies.bill.dto.CreateMovieRequest
import com.movies.bill.models.Movie
import com.movies.bill.models.MovieActor
import com.movies.bill.models.MovieActorId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class MovieService(private val movieRepository: MovieRepository) {
    private val logger = MovieLogger()
    @Autowired
    private lateinit var actorService: ActorService

    // [Request processing failed: org.springframework.dao.InvalidDataAccessApiUsageException: Could not determine appropriate instantiation strategy - no matching constructor found and one or more arguments did not define alias for bean-injection] with root cause
//    @Autowired
//    private lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var movieActorRepository: MovieActorRepository

    fun getAll(): List<Movie> = movieRepository.findAll()

    fun getById(id: Long): Optional<Movie> = movieRepository.findById(id)

    fun getByName(title: String): Movie? {
        val movie = movieRepository.getMovieByTitle(title)
        if (movie != null) {
            return Movie(id = movie.id, title = movie.title, releaseDate = movie.releaseDate)
        }
        return null;
//        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with title '${title}' does not exists")
    }

    @Transactional
    fun createMovie(movieRequest: CreateMovieRequest): Movie {
        val existingMovie = getByName(movieRequest.title)
        if(existingMovie!=null){
            throw IllegalArgumentException("Actor with name ${movieRequest.title} already exists")
        }
        // TODO Ideally, we can cache the ID's in a LRU fashion and return `movie was already created` if the id exists in lru
        val movieObject = Movie(id = movieRequest.id, title = movieRequest.title, releaseDate = movieRequest.releaseDate)
        val createdMovie = movieRepository.save(movieObject)

        for (actor in movieRequest.actors){
            val createdActor = actorService.createActor(actorRequest = actor);
            val movieActor = MovieActor(MovieActorId(movieRequest.title, createdActor.getName()), actor = createdActor, movie = movieObject)
            movieActorRepository.save(movieActor);
        }
        return createdMovie;
    }

//    @Transactional
//    fun createMovieOld(movieRequest: CreateMovieRequest): Movie {
//        if(repository.existsById())
//        var movieObject = Movie(id=movieRequest.id, title = movieRequest.title, releaseDate = movieRequest.releaseDate)
//        var createdMovie = movieRepository.save(movieObject);
//        for (actor in movieRequest.actors){
//            var actorObject = Actor(id=actor.id, name = actor.name)
//            var actor = actorService.createActor(actorRequest = actor);
//            var movieActor = MovieActor(MovieActorId(movieRequest.title, actor.getName()), actor = actor, movie = movieObject)
//            movieActorRepository.save(movieActor);
//        }
//        return createdMovie;
//    }

//    fun createActor(createActorRequest: CreateActorRequest): Actor {
//        val actor;
//        try {
//            actor = actorService.createActor();
//        } catch() {
//
//        }
//        return actor;
//    }
    // create a movie
    // create all actor objects in memory and put in a list
    // actor list iterate and create movie actor objects
    // save movie
    // save actors
    // save movieactor
    // Check uuid id generation
}