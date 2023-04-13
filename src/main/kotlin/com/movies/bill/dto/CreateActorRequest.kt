package com.movies.bill.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CreateActorRequest(
        // TODO add private and getters and setters
        var id: String,
        @JsonProperty("name")
        var name: String
)