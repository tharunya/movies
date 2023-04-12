package com.movies.bill.dao

import com.movies.bill.models.Actor
import com.movies.bill.models.Movie
import com.movies.bill.models.MovieActor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MovieActorRepository : JpaRepository<MovieActor, String> {
    // Find all actors for a given movie
    @Query("SELECT ma.actor FROM MovieActor ma WHERE ma.movie.id = :movieId")
    fun findAllActorsByMovieId(@Param("movieId") movieId: Long): List<Actor>

    // Find all movies for a given actor
    @Query("SELECT ma.movie FROM MovieActor ma WHERE ma.actor.id = :actorId")
    fun findAllMoviesByActorId(@Param("actorId") actorId: Long): List<Movie>
}