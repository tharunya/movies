package com.movies.bill

import com.movies.bill.models.Movie
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.*
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

/***
 * Run application, before the tests
 * Make sure to run the tests in the given order or in an appropriate order, i.e delete a movie before trying to update or create
 * the same movie or just change the request.
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	private val logger = LoggerFactory.getLogger(javaClass)
	var restTemplate =  RestTemplate();
	var resourceUrl:String = "http://localhost:8080/movies";
	val headers = HttpHeaders().apply {
		contentType = MediaType.APPLICATION_JSON
	}

	val request = HttpEntity("""
    {
    "id": 1,
    "title": "Pulp Fiction",
    "releaseDate": "1994–10-14",
    "actors": [
        {
            "id": 1,
            "name": "John Travolta"
        },
        {
            "id": 2,
            "name": "Uma Thurman"
        },
        {
            "id": 3,
            "name": "Samuel L. Jackson"
        }
    ]
}

""".trimIndent(), headers)

	val updateRequest = HttpEntity("""
   {
    "id": 1,
    "title": "Pulp Fiction",
    "releaseDate": "2010–10-19",
    "actors": [
        {
            "id": 8,
            "oldActor":"Leonardo Decaprio",
            "name": "Leonardo da vinci"
        }
    ]
}    

""".trimIndent(), headers)

	@Test
//	@Order(1)
	fun createMovie(){
		val response:Movie? = restTemplate.postForObject(resourceUrl, request, Movie::class.java)
		if (response != null) {
			Assertions.assertEquals(response.getTitle(), "Pulp Fiction")
		}
	}

	@Test
//	@Order(2)
	fun getAllMovies(){
		val responseEntity:ResponseEntity<Array<Movie>> = restTemplate.getForEntity(resourceUrl,Array<Movie>::class.java)
		logger.info("Response body $responseEntity")
		val movies = responseEntity.body
		Assertions.assertEquals(movies?.get(0)?.getTitle() ?: 0,"Pulp Fiction")
	}


	@Test
//	@Order(3)
	// make sure to change the id and name in the update request for every update
	fun updateMovie(){
		try {
			val response: ResponseEntity<Void> = restTemplate.exchange(resourceUrl, HttpMethod.POST, updateRequest, Void::class.java)
			Assertions.assertEquals(201, response.statusCode.value())
		}catch (e: HttpClientErrorException) {
			val responseBody = e.responseBodyAsString
			val responseCode = e.rawStatusCode
			// handle error response
			logger.error(responseBody);
		}
	}
	@Test
//	@Order(4)
	fun deleteMovie(){
		var deleteUrl:String  = resourceUrl+"/"+"deleteMovie?title=Pulp Fiction"
		val response:ResponseEntity<Void> =  restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, Void::class.java)
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(),response.statusCode.value())
	}


}
