package com.movies.bill.models

import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
data class Movie(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long,
        private var releaseDate: String,
        private var title: String,
//        @Column(nullable = true)
//        private var genre: String? = null
): IMedia {
    override fun getId(): Long = id

    // Getter methods
    override fun getReleaseDate(): String = releaseDate

    override fun getTitle(): String = title

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

//    override fun setGenre(newGenre: String) {
//        throw UnsupportedOperationException("Setting Genre is not supported.")
//    }
}
