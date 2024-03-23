package com.example.peopleapps.data.remote.service

import com.example.peopleapps.data.remote.response.PeopleResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/api/users?page=2")
    suspend fun getListPeople(): PeopleResponse

}