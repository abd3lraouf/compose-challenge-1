package com.example.androiddevchallenge.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.PetsTheme


@Composable
@Preview
fun LikeButton(modifier: Modifier = Modifier) {
    val checked = remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .clickable(onClick = { checked.value = !checked.value })
            .background(color = if (checked.value) Color.Red.copy(.5F) else Color.White)
            .padding(8.dp)
    ) {
        Icon(
            Icons.Filled.Favorite,
            tint = if (checked.value) Color.White else PetsTheme.colors.secondary,
            contentDescription = if (checked.value) "Unlike" else "Like"
        )
    }
}