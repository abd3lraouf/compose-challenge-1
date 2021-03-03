package com.example.androiddevchallenge.ui.pets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.ui.utils.NetworkImage

@Composable
fun FavoritePets() {
    Box(modifier = Modifier.fillMaxWidth()) {
        NetworkImage(
            url = "https://partypropz.com/wp-content/uploads/2019/08/CodePen-404-Page.gif",
            contentDescription = "404"
        )
    }
}
