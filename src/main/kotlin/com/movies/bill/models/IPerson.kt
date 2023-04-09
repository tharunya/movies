package com.movies.bill.models

import java.util.*

interface IPerson {

    fun getId(): Long
    fun getName(): String
//    fun getRole(): String?
//    fun getAvailability(): String?
//    fun getGender(): String?
    fun setName(name: String)
//    fun setGender(gender: String)
//    fun setRole(role: String)
//    fun setAvailability(dates: List<Date>)
}
