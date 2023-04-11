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

//    @Transactional
//    @Query("SELECT movie.movie_title, actor.name FROM Actor, Movie WHERE Actor.actor_id = movie_actors.actor_id and movie_actors.movie_id=Movie.movie_id and movie.movie_title=:movie_title")
//    fun getActorsForMovie(@Param("movie_title") movieTitle: String)
//    // TODO check query get all actors where actor movie
//
    @Transactional
    @Query("SELECT a.id, a.name FROM Actor as a WHERE a.name = :name")
    fun getActorByName(@Param("name") name: String): Actor;

//    @Transactional
//    @Query("")
//    fun createActor(@Param("actor") actor: Actor);
//
//    @Transactional
//    @Modifying
//    @Query("")
//    fun updateActor(@Param("id") id: Long, @Param("actor") actor: Actor);
//
//    @Transactional
//    @Modifying
//    @Query("")
//    fun deleteActorById(@Param("id") id: Long);
}