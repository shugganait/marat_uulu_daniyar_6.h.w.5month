package com.kg.love_calculator_beta.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?){
    Glide.with(this).load(url).into(this)
}
