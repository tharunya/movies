package com.movies.bill.models

interface IMedia {

    fun getId(): String?
    fun getReleaseDate(): String
    fun getTitle(): String
//    fun getGenre(): String?
    fun setReleaseDate(newReleaseDate: String)
    fun setTitle(newTitle: String)
//    fun setGenre(newGenre: String)
}