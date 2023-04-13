package com.movies.bill.dao

import com.movies.bill.dto.MovieResponseDto
import com.movies.bill.models.Actor
import com.movies.bill.models.MovieActor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MovieActorRepository : JpaRepository<MovieActor, String> {
    // Find all actors for a given movie
    @Query("SELECT new com.movies.bill.dto.MovieResponseDto(m.id, m.title, m.releaseDate, a.id, a.name) FROM MovieActor ma, Movie m, Actor a WHERE ma.actor.name = a.name AND ma.movie.title = :title")
    fun getMovieAndActors(@Param("title") title: String): List<MovieResponseDto>?

    // Find all movies for a given actor
//    @Query("SELECT ma.movie FROM MovieActor ma WHERE ma.actor.id = :actorId")
//    fun findAllMoviesByActorId(@Param("actorId") actorId: Long): List<Movie>
}