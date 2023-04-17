package com.movies.bill

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class MovieLogger {
    val logger: Logger = LogManager.getLogger("MovieAppLogger")
    fun info(message: String) {
        logger.info(message)
    }

    fun error(message: String, throwable: Throwable) {
        logger.error(message, throwable)
    }
}