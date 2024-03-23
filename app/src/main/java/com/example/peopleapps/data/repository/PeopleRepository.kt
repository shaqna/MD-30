package com.example.peopleapps.data.repository

import android.content.Context
import com.example.peopleapps.data.remote.service.ApiService
import com.example.peopleapps.model.People
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PeopleRepository(
    private val apiService: ApiService,
) {
    fun getPeopleList(): Flow<Result<List<People>>> {
        return flow {
            val response = apiService.getListPeople()
            if(response.data.isEmpty()) {
                emit(Result.failure(Exception("Data Kosong")))
            }
            emit(Result.success(response.data))
        }.flowOn(Dispatchers.IO)
    }
}