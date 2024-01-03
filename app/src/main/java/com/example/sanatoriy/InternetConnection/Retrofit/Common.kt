package com.example.myroutekotlin.InternetConnection.Retrofit

object Common {
    private const val BASE_URL = "http://31.129.103.95:8111/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}
//http://31.129.103.95:8111/dish/all/typeOfDish