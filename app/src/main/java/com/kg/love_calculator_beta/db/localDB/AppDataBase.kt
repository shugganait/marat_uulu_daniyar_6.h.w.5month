package com.kg.love_calculator_beta.db.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kg.love_calculator_beta.model.Love

@Database(entities = [Love::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loveDao(): LoveDao
}