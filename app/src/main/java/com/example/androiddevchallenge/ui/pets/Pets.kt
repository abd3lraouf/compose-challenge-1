/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.pets

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.primarySurface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.allPets
import com.example.androiddevchallenge.ui.common.OutlinedAvatar
import com.example.androiddevchallenge.ui.theme.PetsTheme
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun Pets(lovePet: (Long) -> Unit, adoptPet: (Long) -> Unit) {
    PetsTheme {
        val (selectedTab, setSelectedTab) = remember { mutableStateOf(PetsTab.Home as PetsTab) }
        val tabs = arrayOf(PetsTab.Home, PetsTab.Favorite)
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            bottomBar = {
                BottomNavigation(Modifier.navigationBarsHeight(additional = 56.dp)) {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                            label = { Text(stringResource(tab.title).toUpperCase()) },
                            selected = tab == selectedTab,
                            onClick = { setSelectedTab(tab) },
                            alwaysShowLabel = false,
                            selectedContentColor = MaterialTheme.colors.secondary,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            when (selectedTab) {
                PetsTab.Home -> AllPets(modifier, allPets, lovePet, adoptPet)
                PetsTab.Favorite -> FavoritePets()
            }
        }
    }
}

@Preview
@Composable
fun PetsAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        elevation = 0.dp,
        modifier = modifier.height(56.dp),
    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.adopt),
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { /* todo */ }
            ) {
                NotificationIcon()
            }

            OutlinedAvatar(
                url = "https://0.gravatar.com/avatar/bd13d45ee871bb17bffab6f1e18f73e9?s=200",
                outlineColor = MaterialTheme.colors.surface,
                outlineSize = 0.dp,
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.CenterVertically)
                    .clickable(
                        onClick = { },
                        role = Role.Button,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false)

                    )
            )
        }
    }
}

sealed class PetsTab(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    object Home : PetsTab(R.string.home, R.drawable.ic_home)
    object Favorite : PetsTab(R.string.favorites, R.drawable.ic_favorite)
}

@Composable
@Preview
fun NotificationIcon(
    modifier: Modifier = Modifier,
    text: String = "1"
) {
    Box(modifier = Modifier.size(24.dp)) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notification",
            modifier = Modifier.rotate(315F)
        )
        NotificationCount(
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
    }
}

@Composable
fun NotificationCount(
    modifier: Modifier = Modifier,
    text: String = "1"
) {
    Box(
        modifier = modifier
            .size(13.dp)
            .clip(CircleShape)
            .background(color = Color.Red)
    ) {
        Text(
            text,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 10.sp
        )
    }
}
