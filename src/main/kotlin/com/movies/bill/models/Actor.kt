package com.movies.bill.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Actor(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                 private var id: Long,
                 private var name: String) :IPerson {
//                 @Column(nullable = true)
//                 private var gender: String? = null,
//                 @Column(nullable = true)
//                 private var role: String? = null,
//                 @Column(nullable = true)
//                 private var availability: String? = null) : IPerson {

    override fun getId(): Long = id
    override fun getName(): String = name
//    override fun getGender(): String? = gender
//    override fun getRole(): String? {
//        throw UnsupportedOperationException("This operation is not supported.")
//    }
//    override fun getAvailability(): String? {
//        throw UnsupportedOperationException("This operation is not supported.")
//    }

    // Setters (custom implementation)
    override fun setName(name: String) {
        this.name = name
    }

//    override fun setGender(gender: String) {
//        this.gender = gender
//    }

//    override fun setRole(role: String) {
//        throw UnsupportedOperationException("This operation is not supported.")
//    }

//    override fun setAvailability(dates: List<Date>) {
//        throw UnsupportedOperationException("This operation is not supported.")
//    }
}