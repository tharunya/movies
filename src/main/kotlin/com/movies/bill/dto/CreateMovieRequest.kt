package com.movies.bill.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateMovieRequest (
        var id: String,
        var title: String,
        var releaseDate: String,
        @JsonProperty("actors")
        var actors: ArrayList<CreateActorRequest>
)