package com.movies.bill.dao

import com.movies.bill.dto.MovieResponseDto
import com.movies.bill.models.Actor
import com.movies.bill.models.MovieActor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface MovieActorRepository : JpaRepository<MovieActor, String> {
    // Find all actors for a given movie
    @Query("SELECT new com.movies.bill.dto.MovieResponseDto(m.id, m.title, m.releaseDate, a.id, a.name) FROM MovieActor ma, Movie m, Actor a WHERE ma.actor.name = a.name AND ma.movie.title = :title")
    fun getMovieAndActors(@Param("title") title: String): List<MovieResponseDto>?

    @Modifying
    @Transactional
    @Query("UPDATE MovieActor ma set ma.actor.name=:name where ma.movie.title=:title and ma.actor.name=:oldActor")
    fun updateActor(@Param("name") name: String, @Param("title") title: String, @Param("oldActor") oldActor: String)

    @Modifying
    @Transactional
    @Query("DELETE MovieActor ma where ma.movie.title=:title")
    fun deleteByName(@Param("title") title: String)
}