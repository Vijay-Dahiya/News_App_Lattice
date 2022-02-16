package com.vijay.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vijay.newsapp.model.Repo
import com.vijay.newsapp.model.remote.ArticlesItem

/**
 * This is our View Model class.
 * It is responsible to store the instance of data so that whenever we rotate our screen
 * activity will recreated but data remains same no needs to fetch again from server.
 * Here we are getting data from repo and sending it to our main activity in a lifecycle conscious way
 * in the form of liveData.
 */

class TheViewModel(private val repo: Repo) : ViewModel() {

    fun getData(): LiveData<List<ArticlesItem?>> {
        return repo.getDataFromApi()
    }
}

class ViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TheViewModel(repo) as T
    }
}