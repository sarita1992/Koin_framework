package com.example.unlimint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unlimint.utils.Constants
import com.example.unlimint.utils.PrefsManager
import com.example.unlimint.R
import com.example.unlimint.databinding.LayoutUserAdapterBinding
import com.google.gson.Gson
import java.util.ArrayList

class JokesAdapter(val userData: ArrayList<String>) : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    var userList = mutableListOf<String>()

    init {
        userList = userData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding =
            LayoutUserAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(binding)
    }


    override fun onViewRecycled(holder: JokesViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        var i = position
        i = (i + 1)
        with(holder) {
            binding.tvJoke.text = ("" + i + ") " + userList[position])
        }

    }

    fun setItems(prefManager: PrefsManager, it: String) {
        if (userList.size == 10) {
            userList.removeAt(0)
        }
        userList.add(it)
        val gson = Gson()
        val json = gson.toJson(userList)
        prefManager!!.setData(Constants.STORED_DATA, json)
        notifyDataSetChanged()
    }


    inner class JokesViewHolder(val binding: LayoutUserAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}