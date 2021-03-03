package com.example.androiddevchallenge.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.androiddevchallenge.ui.utils.LocalBackDispatcher
import com.example.androiddevchallenge.ui.utils.ProvideImageLoader
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun PetsApp(backDispatcher: OnBackPressedDispatcher) {
    CompositionLocalProvider(LocalBackDispatcher provides backDispatcher) {
        ProvideWindowInsets {
            ProvideImageLoader {
                NavGraph()
            }
        }
    }
}
