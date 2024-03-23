package com.example.peopleapps.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopleapps.data.repository.PeopleRepository
import com.example.peopleapps.utils.Dummy
import com.example.peopleapps.model.People
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf

class MainViewModel(
    private val peopleRepository: PeopleRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Init)
    val uiState: StateFlow<MainUiState> = _uiState

    private val _stateUi: MutableLiveData<MainUiState> = MutableLiveData()
    val stateUi: LiveData<MainUiState> = _stateUi

//    fun getPeoples() : List<People> = Dummy.listPeoples

    fun getPeople() {
        viewModelScope.launch {
            peopleRepository.getPeopleList()
                .onStart {
                    _uiState.value = MainUiState.Loading(true)

                }
                .catch {
                    _uiState.value = MainUiState.Loading(false)
                    _uiState.value = MainUiState.Error("Data kosong")
                }
                .collect {
                    _uiState.value = MainUiState.Loading(false)
                    it.fold(
                        onFailure = {
                            _uiState.value = MainUiState.Error("Data kosong")
                        },
                        onSuccess = { listPeople ->
                            _uiState.value = MainUiState.Success(listPeople)
                        }
                    )
                }
        }
    }


    companion object {
        fun inject() = module {
            viewModelOf(::MainViewModel)
        }
    }
}

sealed class MainUiState {
    data object Init: MainUiState()
    data class Loading(val isLoading: Boolean): MainUiState()
    data class Error(val message: String): MainUiState()
    data class Success(val listPeople: List<People>): MainUiState()
}
