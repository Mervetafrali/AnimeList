package com.example.animelist.helpers

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.model.Animes
import com.example.animelist.service.AnimesService
import com.example.animelist.service.ServiceBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimesView : ViewModel() {
    private val _animeList = mutableStateListOf<Animes>()
    var errorMessage: String by mutableStateOf("")
    val animeList: List<Animes>
        get() = _animeList

    fun getTodoList() {
        viewModelScope.launch {
            val destinationService = ServiceBuilder.buildService(AnimesService::class.java)
            val requestCall = destinationService.getAnimeList()

            requestCall.enqueue(object : Callback<List<Animes>> {
                override fun onResponse(
                    call: Call<List<Animes>>,
                    response: Response<List<Animes>>
                ) {
                    Log.d("Response", "onResponse: ${response.body()}")
                    if (response.isSuccessful) {
                        val animeL = response.body()!!
                        try {
                            _animeList.clear()
                            _animeList.addAll(animeL)

                        } catch (e: Exception) {
                            errorMessage = e.message.toString()
                        }

                    } else {
                        Log.d("Response", "hata var  ${response}")
                    }
                }

                override fun onFailure(call: Call<List<Animes>>, t: Throwable) {
                    Log.d("Response", "Something went wrong $t")
                }
            })
        }

    }
}
