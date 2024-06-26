package com.example.peopleapps.data.di

import com.example.peopleapps.data.remote.service.ApiService
import com.example.peopleapps.data.repository.PeopleRepository
import com.example.peopleapps.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val retrofit = module {
    single {
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            ).build()

        retrofit.create(ApiService::class.java)
    }
}

val dataModule = module {
    single {
        PeopleRepository(get())
    }
}