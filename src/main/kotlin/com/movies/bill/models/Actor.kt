package com.movies.bill.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
data class Actor(
    @NotNull
    @Column(unique = true)
    private var id: String,
    @Id
    @Column(name="actor_name")
    private var name: String
) :IPerson {

    override fun getId(): String = id
    override fun getName(): String = name

    // Setters (custom implementation)
    override fun setName(name: String) {
        this.name = name
    }
}