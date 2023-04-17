package com.movies.bill.dao

import com.movies.bill.dto.CreateActorRequest
import com.movies.bill.models.Actor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface ActorRepository : JpaRepository<Actor, String> {
    @Transactional
    @Query("SELECT new com.movies.bill.dto.CreateActorRequest(a.id, a.name,'') FROM Actor a WHERE a.name = :name")
    fun getByName(@Param("name") name: String): CreateActorRequest?;

    @Transactional
    @Query("SELECT a.name from Actor a left join MovieActor ma  on a.name = ma.actor.name where ma.actor.name IS NULL")
    fun getOrphanedActors(): List<String>;

    @Transactional
    @Modifying
    @Query("DELETE FROM Actor where name=:name")
    fun deleteActor(@Param("name") name: String);
}
