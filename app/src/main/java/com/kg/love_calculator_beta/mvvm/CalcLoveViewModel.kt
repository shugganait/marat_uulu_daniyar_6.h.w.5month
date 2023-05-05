package com.kg.love_calculator_beta.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kg.love_calculator_beta.remote.LoveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalcLoveViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun liveLove(fName: String, sName: String): LiveData<LoveModel> {
        return repository.getLove(fName,sName)
    }
}