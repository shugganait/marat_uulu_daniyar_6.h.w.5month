package com.kg.love_calculator_beta.DB.localDB

import androidx.lifecycle.ViewModel
import com.kg.love_calculator_beta.model.Love
import com.kg.love_calculator_beta.mvvm.Repository
import javax.inject.Inject

class DaoViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun insertLove(love: Love) { repository.callDaoInsert(love) }

    fun updateLove(love: Love)  { repository.callDaoUpdate(love) }

    fun getAllLove(): List<Love> {
        return repository.callDaoGetAll()
    }
}