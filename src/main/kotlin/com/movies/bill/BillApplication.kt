package com.movies.bill

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BillApplication

fun main(args: Array<String>) {
	runApplication<BillApplication>(*args)
}
