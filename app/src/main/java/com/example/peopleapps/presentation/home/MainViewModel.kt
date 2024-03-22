package com.example.peopleapps.presentation.home

import androidx.lifecycle.ViewModel
import com.example.peopleapps.data.Dummy
import com.example.peopleapps.model.People

class MainViewModel: ViewModel() {
    fun getPeoples() : List<People> = Dummy.listPeoples
}