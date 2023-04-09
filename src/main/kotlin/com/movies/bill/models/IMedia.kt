package com.movies.bill.models

interface IMedia {

    fun getId(): Long
    fun getReleaseDate(): String
    fun getTitle(): String
//    fun getGenre(): String?
    fun setReleaseDate(newReleaseDate: String)
    fun setTitle(newTitle: String)
//    fun setGenre(newGenre: String)
}