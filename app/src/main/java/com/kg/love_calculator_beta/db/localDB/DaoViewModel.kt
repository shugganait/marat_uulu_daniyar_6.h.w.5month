package com.kg.love_calculator_beta.db.localDB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kg.love_calculator_beta.model.Love
import com.kg.love_calculator_beta.mvvm.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DaoViewModel @Inject constructor(private val repository: Repository): ViewModel() {

//    fun getAllLove() = repository.daoGetAll()
//
//    fun insertLove(love: Love) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.daoInsert(love)
//        }
//    }
//
//    fun updateLove(love: Love) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.daoInsert(love)
//        }
//    }
}