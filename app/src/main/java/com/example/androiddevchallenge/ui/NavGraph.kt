/*
 * Copyright 2020 The Android Open Source Project
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

package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.ui.MainDestinations.PET_DETAIL_ID_KEY
import com.example.androiddevchallenge.ui.pet.PetDetails
import com.example.androiddevchallenge.ui.pets.Pets

/**
 * Destinations used in the ([PetsApp]).
 */
object MainDestinations {
    const val PETS_ROUTE = "pets"
    const val PET_DETAIL_ROUTE = "pet"
    const val PET_DETAIL_ID_KEY = "petId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.PETS_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.PETS_ROUTE) {
            Pets(lovePet = actions.lovePet, adoptPet = actions.adoptPet)
        }
        composable(
            "${MainDestinations.PET_DETAIL_ROUTE}/{$PET_DETAIL_ID_KEY}",
            arguments = listOf(navArgument(PET_DETAIL_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            PetDetails(
                petId = arguments.getLong(PET_DETAIL_ID_KEY),
                lovePet = actions.lovePet,
                adoptPet = actions.lovePet,
                upPress = actions.upPress
            )
        }
    }
}


/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val lovePet: (Long) -> Unit = { petId: Long ->
        navController.navigate("${MainDestinations.PET_DETAIL_ROUTE}/$petId")
    }
    val adoptPet: (Long) -> Unit = { petId: Long ->
        navController.navigate("${MainDestinations.PET_DETAIL_ROUTE}/$petId")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
