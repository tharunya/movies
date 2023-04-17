package com.movies.bill.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
data class Movie(
                @NotNull
                @Column(unique=true)
                private var id: String,
                @Id
                @Column(name="title")
                private var title: String,
                @Column(unique=true)
                private var releaseDate: String): IMedia {

    // Getter methods
    override fun getId(): String = id
    override fun getTitle(): String = title
    override fun getReleaseDate(): String = releaseDate

    // Setter methods
    override fun setTitle(newTitle: String) {
        title = newTitle
    }
    override fun setReleaseDate(newReleaseDate: String) {
        releaseDate = newReleaseDate
    }

}
