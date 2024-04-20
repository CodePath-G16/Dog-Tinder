package com.example.dog_tinder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _dogInfoList = MutableLiveData<List<HomeFragment.DogInfo>>()
    val dogInfoList: LiveData<List<HomeFragment.DogInfo>> = _dogInfoList

    fun setDogInfoList(dogInfoList: List<HomeFragment.DogInfo>) {
        _dogInfoList.value = dogInfoList
    }
}


