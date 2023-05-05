package com.kg.love_calculator_beta.mvvm

import androidx.lifecycle.MutableLiveData
import com.kg.love_calculator_beta.DB.localDB.AppDatabase
import com.kg.love_calculator_beta.DB.remote.LoveApi
import com.kg.love_calculator_beta.DB.remote.LoveModel
import com.kg.love_calculator_beta.model.Love
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val api: LoveApi, private val dao: AppDatabase) {
    fun getLove(fName:String, sName:String): MutableLiveData<LoveModel> {
        val liveLove = MutableLiveData<LoveModel>()

        api.percentageName(fName, sName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if(response.isSuccessful){
                    liveLove.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<LoveModel>, t: Throwable) {

            }
        })
        return liveLove
    }

    fun callDaoInsert(love: Love) {
        dao.loveDao().insert(love)
    }
    fun callDaoGetAll(): List<Love> {
        return dao.loveDao().getAll()
    }
    fun callDaoUpdate(love: Love) {
        dao.loveDao().update(love)
    }
}