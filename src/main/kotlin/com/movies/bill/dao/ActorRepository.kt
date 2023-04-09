package com.movies.bill.dao

import com.movies.bill.models.Actor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ActorRepository : JpaRepository<Actor, Long>{

    @Transactional
    @Modifying
    @Query("SELECT movie.movie_title, actor.name FROM actor, movie WHERE actor.actor_id = movie_actors.actor_id and movie_actors.movie_id=movie.movie_id and movie.movie_title=:movie_title")
    fun getActorsForMovie(@Param("movie_title") movieTitle: String)
    // TODO check query get all actors where actor movie

    @Transactional
    @Modifying
    @Query("")
    fun getActorById(@Param("id") id: Long);

    @Transactional
    @Modifying
    @Query("")
    fun createActor(@Param("id") id: Long, @Param("actor") actor: Actor);

    @Transactional
    @Modifying
    @Query("")
    fun updateActor(@Param("id") id: Long, @Param("actor") actor: Actor);

    @Transactional
    @Modifying
    @Query("")
    fun deleteActorById(@Param("id") id: Long);
}