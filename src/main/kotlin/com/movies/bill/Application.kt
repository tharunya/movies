package com.movies.bill

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	/**
	 * Exceptions (custom) handle exceptions with response status codes
	 * Unit tests
	 * clean up code
	 * add docs for methods
	 * UUID?
	 */
	runApplication<Application>(*args)
}
