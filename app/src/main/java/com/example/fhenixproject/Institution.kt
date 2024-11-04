package com.example.fhenixproject

import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Institution (
    @DrawableRes var imageRes: Int,
    var name: String,var subject: String,
    var description: String,
    //@ColorRes var color: Int
)