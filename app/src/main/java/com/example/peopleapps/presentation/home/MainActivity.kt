package com.example.peopleapps.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peopleapps.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

//    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by viewModel()

    private val peopleAdapter: PeopleAdapter by lazy {
        PeopleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(MainViewModel.inject())

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupRecyclerView()
        fetchData()
        observeData()
//        setData()
    }

    private fun fetchData() {
        viewModel.getPeople()
    }

    private fun observeData() {
        viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
            .onEach {
                handleState(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: MainUiState) {
        when(state) {
            is MainUiState.Error -> {
                Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
            }
            MainUiState.Init -> Unit
            is MainUiState.Loading -> {
                showLoading(state.isLoading)
            }
            is MainUiState.Success -> {
                peopleAdapter.setList(state.listPeople)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun setupRecyclerView() {
        binding.rvPeoples.apply {
            adapter = peopleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

//    private fun setData() {
//        val listPeople = viewModel.getPeoples()
//        peopleAdapter.setList(listPeople)
//    }

}