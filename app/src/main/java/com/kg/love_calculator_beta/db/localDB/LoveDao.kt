package com.kg.love_calculator_beta.db.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kg.love_calculator_beta.model.Love

@Dao
interface LoveDao {

    @Insert
    fun insert(love: Love)

    @Query("SELECT * FROM Love ORDER BY firstName ASC")
    fun getAll(): List<Love>

    @Update
    fun update(love: Love)
}