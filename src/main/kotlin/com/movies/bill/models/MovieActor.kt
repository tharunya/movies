package com.movies.bill.models

import jakarta.persistence.*
import java.io.Serializable

@Entity
data class MovieActor(
        // Composite key
        @EmbeddedId
        val id: MovieActorId,

        @ManyToOne
        @MapsId("title")
        @JoinColumn(name = "title")
        val movie: Movie,

        @ManyToOne
        @MapsId("actorName")
        @JoinColumn(name = "actor_name")
        val actor: Actor
)

@Embeddable
data class MovieActorId(
        @Column(name = "title")
        val title: String,
        @Column(name = "actor_name")
        val actorName: String
) : Serializable



