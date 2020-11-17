package com.newapp.fruitsy.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
var timerInterceptor = TimerInterceptor()
val appClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .addInterceptor(timerInterceptor)
    .build()

val fruitService: FruitService by lazy {
    Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .client(appClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(FruitService::class.java)
}

val loggingClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

val appStatsService: AppStatsService by lazy {
    Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .client(loggingClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(AppStatsService::class.java)
}