package com.movies.bill.models

import jakarta.persistence.*

@Entity
data class Movie(
                // TODO UUID?
                @Column(unique=true)
                private var id: String,
                @Column(unique=true)
                private var releaseDate: String,
                @Id
                @Column(name="title")
                private var title: String): IMedia {
    override fun getId(): String = id

    // Getter methods
    override fun getReleaseDate(): String = releaseDate

    override fun getTitle(): String = title

//    fun getMovieActors(): Set<MovieActor> = movieActors

//    override fun getGenre(): String? {
//        throw UnsupportedOperationException("Genre is not supported.")
//    }

    // Setter methods
    override fun setReleaseDate(newReleaseDate: String) {
        releaseDate = newReleaseDate
    }

    override fun setTitle(newTitle: String) {
        title = newTitle
    }

//    fun setMovieActors(movieActors: Set<MovieActor>) {
//        this.movieActors = movieActors
//    }

//    override fun setGenre(newGenre: String) {
//        throw UnsupportedOperationException("Setting Genre is not supported.")
//    }
}
