package com.movies.bill.dao

import com.movies.bill.dto.CreateMovieRequest
import com.movies.bill.models.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface MovieRepository : JpaRepository<Movie, String> {
    @Transactional
    @Query("SELECT new com.movies.bill.dto.CreateMovieRequest(m.id, m.title, m.releaseDate) FROM Movie m WHERE m.title = :title")
    fun getMovieByTitle(@Param("title") title: String): CreateMovieRequest?;
}