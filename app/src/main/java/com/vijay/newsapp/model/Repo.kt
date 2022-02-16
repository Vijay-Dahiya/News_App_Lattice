package com.vijay.newsapp.model

import androidx.lifecycle.liveData
import com.vijay.newsapp.model.remote.ArticlesItem
import com.vijay.newsapp.model.remote.Network

/**
 * This is our repository class used to collect data from server and send that data to
 * ViewModel class.
 * We getting data inside try-catch to avoid crashing when there is some error or network unAvailability
 */

class Repo() {

    fun getDataFromApi()= liveData<List<ArticlesItem?>> {
        try {
            emit(Network.apiClient.getData().articles!!)
        }catch (e:Exception){

        }
    }
}