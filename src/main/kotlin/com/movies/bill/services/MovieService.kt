package com.movies.bill.services

import com.movies.bill.MovieLogger
import com.movies.bill.dao.MovieActorRepository
import com.movies.bill.dao.MovieRepository
import com.movies.bill.dto.CreateMovieRequest
import com.movies.bill.models.Actor
import com.movies.bill.models.Movie
import com.movies.bill.models.MovieActor
import com.movies.bill.models.MovieActorId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MovieService(private val repository: MovieRepository) {
    private val logger = MovieLogger()
    @Autowired
    private lateinit var actorService: ActorService
    @Autowired
    private lateinit var movieActorRepository: MovieActorRepository

    fun getAll(): List<Movie> = repository.findAll()

    fun getById(id: Long): Optional<Movie> = repository.findById(id)

    fun getByName(title: String): Movie? {
        try {
            var movie = repository.getMovieByTitle(title).orElse(null)
            if(movie!=null){
                return movie;
            } else {
                logger.info(" MOVIE IS NULL ? ")
                // throw custom exception MovieNotFound
            }
        } catch (ex: EmptyResultDataAccessException) {
            ex.message?.let { logger.error(it, ex) };
        }
        return null;
    }

    @Transactional
    fun createMovie(movieRequest: CreateMovieRequest): Movie {
        // TODO handle exception with response status codes
        // TODO handle id here generate uuid instead?
        // TODO Ideally, we can cache the values in a LRU fashion and return movie was created if the id exists in lru
//        if(repository.existsById())
        var movieObject = Movie(id=movieRequest.id, title = movieRequest.title, releaseDate = movieRequest.releaseDate)
        var createdMovie = repository.save(movieObject);
        for (actor in movieRequest.actors){
            var actorObject = Actor(id=actor.id, name = actor.name)
            var actor = actorService.createActor(actorObject);
            var movieActor = MovieActor(MovieActorId(movieRequest.title, actor.getName()), actor = actorObject, movie = movieObject)
            movieActorRepository.save(movieActor);
        }
        return createdMovie;
    }

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