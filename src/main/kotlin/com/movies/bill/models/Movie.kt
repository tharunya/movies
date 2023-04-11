package com.movies.bill.models

import jakarta.persistence.*

@Entity
data class Movie(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="movie_id")
        private var id: Long,
        @Column(unique=true)
        private var releaseDate: String,
        @Column(unique=true)
        private var title: String,
        @OneToMany(mappedBy = "movie")
        private var movieActors: Set<MovieActor> = HashSet()): IMedia {
    //        @Column(nullable = true)
    //        private var genre: String? = null
    override fun getId(): Long = id

    // Getter methods
    override fun getReleaseDate(): String = releaseDate

    override fun getTitle(): String = title

    fun getMovieActors(): Set<MovieActor> = movieActors

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

    fun setMovieActors(movieActors: Set<MovieActor>) {
        this.movieActors = movieActors
    }

//    override fun setGenre(newGenre: String) {
//        throw UnsupportedOperationException("Setting Genre is not supported.")
//    }
}
