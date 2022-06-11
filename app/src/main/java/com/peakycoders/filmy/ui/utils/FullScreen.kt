package com.peakycoders.filmy.ui.utils

import android.app.Activity
import android.view.WindowManager

@Suppress("DEPRECATION")
fun Activity.fullScreen(){
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
}