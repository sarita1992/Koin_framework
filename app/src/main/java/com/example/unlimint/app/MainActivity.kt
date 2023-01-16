package com.example.unlimint.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.unlimint.viewModel.MainViewModel
import com.example.unlimint.adapter.JokesAdapter
import com.example.unlimint.databinding.ActivityMainBinding
import com.example.unlimint.utils.Constants
import com.example.unlimint.utils.PrefsManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val scope =
        MainScope() // could also use an other scope such as viewModelScope if available
    var job: Job? = null
    lateinit var jokesAdapter: JokesAdapter
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private var prefManager: PrefsManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        prefManager = PrefsManager(this)
        getDataFromLocal()
        mainViewModel.userList.observe(this, Observer {
            it?.let {
                jokesAdapter.setItems(prefManager!!, it)
            }
        })

        lifecycleScope.launchWhenResumed { startUpdates() }
    }


    private fun getDataFromLocal() {
        val gson = Gson()
        val json: String = prefManager!!.getData(Constants.STORED_DATA, "")
        val type = object : TypeToken<ArrayList<String?>?>() {}.type
        var arrayList = gson.fromJson<ArrayList<String>>(json, type)
        if (arrayList == null) arrayList = ArrayList<String>()
        jokesAdapter = JokesAdapter(arrayList)
        binding.rvJokes.adapter = jokesAdapter
        binding.rvJokes.addItemDecoration(
            DividerItemDecoration(
                binding.rvJokes.getContext(), DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun startUpdates() {
        stopUpdates()
        job = scope.launch {
            while (true) {
                mainViewModel.fetchUsers() // the function that should be ran every minute
                delay(60000)
            }
        }
    }

    private fun stopUpdates() {
        job?.cancel()
        job = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopUpdates()
    }
}