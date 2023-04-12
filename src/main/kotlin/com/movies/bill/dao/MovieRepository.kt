package com.movies.bill.dao

import com.movies.bill.models.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface MovieRepository : JpaRepository<Movie, Long> {
    @Transactional
    @Query("SELECT m.id, m.title FROM Movie as m WHERE m.title = :title")
    fun getMovieByTitle(@Param("title") title: String): Optional<Movie>;
}