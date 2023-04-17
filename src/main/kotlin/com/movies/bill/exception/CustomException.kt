package com.movies.bill.exception

data class CustomErrorResponse(val errorCode: String, val errorMessage: String)

class CustomException(val errorResponse: CustomErrorResponse) : RuntimeException()