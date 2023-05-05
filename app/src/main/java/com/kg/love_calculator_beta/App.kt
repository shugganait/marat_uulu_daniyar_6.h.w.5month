package com.kg.love_calculator_beta

import android.app.Application
import androidx.room.Room
import com.kg.love_calculator_beta.DB.localDB.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import java.util.function.DoubleBinaryOperator

@HiltAndroidApp
class App: Application() {
}