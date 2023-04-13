package com.movies.bill.services

import com.movies.bill.MovieLogger
import com.movies.bill.dao.MovieActorRepository
import com.movies.bill.dao.MovieRepository
import com.movies.bill.dto.CreateActorRequest
import com.movies.bill.dto.CreateMovieRequest
import com.movies.bill.dto.MovieResponseDto
import com.movies.bill.models.Actor
import com.movies.bill.models.Movie
import com.movies.bill.models.MovieActor
import com.movies.bill.models.MovieActorId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService {
    private val logger = MovieLogger()
    @Autowired
    private lateinit var actorService: ActorService

    @Autowired
    private lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var movieActorRepository: MovieActorRepository

    fun getAll(): List<Movie> = movieRepository.findAll()

//    fun getById(id: String): Optional<Movie> = movieRepository.findById(id)

    fun movieExists(title: String): Movie? {
        val movie = movieRepository.getMovieByTitle(title)
        if (movie != null) {
            return Movie(movie.id, movie.title, movie.releaseDate)
        }
        logger.info("Movie with title '${title}' does not exists")
        return null;
    }

    fun getMovieByName(title: String): CreateMovieRequest? {
        val movieResponseDto = movieActorRepository.getMovieAndActors(title)
        if (movieResponseDto != null) {
            return convertMovieResponse(movieResponseDto)
        }
        return null;
    }

    // make private
    fun convertMovieResponse(movieResponseDto: List<MovieResponseDto>): CreateMovieRequest {
        val movieId = movieResponseDto.get(0).movieid
        val movieTitle = movieResponseDto.get(0).movietitle
        val movieReleaseDate = movieResponseDto.get(0).moviereleaseDate
        val actorList = ArrayList<CreateActorRequest>()
        for (movieResponse in movieResponseDto) {
            actorList.add(CreateActorRequest(movieResponse.actorId, movieResponse.actorName))
        }

        return CreateMovieRequest(movieId, movieTitle, movieReleaseDate, actorList)
    }

    @Transactional
    fun createMovie(movieRequest: CreateMovieRequest): Movie {
        val existingMovie = movieExists(movieRequest.title)
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
}