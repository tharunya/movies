package com.movies.bill.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
data class Actor(
//                 @GeneratedValue(generator = "UUID")
//                 @GenericGenerator(
//                     name = "UUID",
//                     strategy = "org.hibernate.id.UUIDGenerator"
//                 )
                 @NotNull
                 @Column(unique = true)
                 private var id: String,
                 @Id
                 @Column(name="actor_name")
                 private var name: String,
//                 @OneToMany(mappedBy = "actor")
//                 val movieActors: Set<MovieActor> = HashSet()
                ) :IPerson {
//                 @Column(nullable = true)
//                 private var gender: String? = null,
//                 @Column(nullable = true)
//                 private var role: String? = null,
//                 @Column(nullable = true)
//                 private var availability: String? = null) : IPerson {

    override fun getId(): String? = id
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