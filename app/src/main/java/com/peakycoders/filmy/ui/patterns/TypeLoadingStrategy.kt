package com.peakycoders.filmy.ui.patterns

import androidx.compose.runtime.Composable
import com.peakycoders.filmy.ui.utils.ShimmerAnimationHorizontal
import com.peakycoders.filmy.ui.utils.ShimmerAnimationVertical

interface TypeLoading {
    @Composable
    fun Show()

    fun name() : String
}

class LoadingVertical() : TypeLoading {
    @Composable
    override fun Show() = ShimmerAnimationVertical()

    override fun name() = "LoadingVertical"
}

class LoadingHorizontal() : TypeLoading {
    @Composable
    override fun Show() = ShimmerAnimationHorizontal()

    override fun name() = "LoadingHorizontal"
}