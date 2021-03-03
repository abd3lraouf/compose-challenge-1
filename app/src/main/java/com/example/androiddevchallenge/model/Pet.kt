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
package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.androiddevchallenge.R

@Immutable
data class Pet(
    val id: Long,
    val name: String,
    val category: PetCategory,
    val thumbUrl: String,
    val thumbContentDesc: String,
    val description: String,
    val owner: PetOwner,
    val likes: Int,
    val address: String,
    val age: Int,
    val weight: Float,
    val color: String,
)

sealed class PetCategory(
    @StringRes val name: Int,
    @DrawableRes val icon: Int
) {
    object Cat : PetCategory(R.string.cats, R.drawable.ic_cat)
    object Dog : PetCategory(R.string.dpgs, R.drawable.ic_dog)
}

@Immutable
data class PetOwner(
    val name: String,
    val thumbUrl: String,
    val thumbContentDesc: String,
)

object PetRepo {
    fun getPet(petId: Long): Pet = allPets.find { it.id == petId }!!

    fun getRelated(petId: Long): List<Pet> {
        val petType = getPet(petId).category
        return allPets
            .filter { it.category == petType }
            .shuffled()
    }
}

fun randomDescriptionCount() = (50 until 70).random()
fun randomLikes() = (0 until 10000).random()
fun randomAmount() = (10 until 70).random()
fun randomAge() = (10 until 35).random()
fun randomWeight() = (15 until 25).random().toFloat()

val owners = listOf(
    PetOwner(
        "Daniel Smith",
        "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=500&h=500",
        "Lisa Smith's image"
    ),
    PetOwner(
        "Heather King",
        "https://images.unsplash.com/photo-1554151228-14d9def656e4?w=500&h=500",
        "Heather King's image"
    ),
    PetOwner(
        "Donna Martin",
        "https://images.unsplash.com/photo-1604426633861-11b2faead63c?w=500&h=500",
        "Donna Martin's image"
    ),
    PetOwner(
        "Anderson Cook",
        "https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?w=500&h=500",
        "Sandra Cook's image"
    ),
    PetOwner(
        "Laura Perez",
        "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=500&h=500",
        "Laura Perez's image"
    ),
    PetOwner(
        "Carol Roberts",
        "https://images.unsplash.com/photo-1557296387-5358ad7997bb?w=500&h=500",
        "Carol Roberts's image"
    ),
    PetOwner(
        "Charles Bryant",
        "https://images.unsplash.com/photo-1592124549776-a7f0cc973b24?w=500&h=500",
        "Charles Bryant's image"
    ),
    PetOwner(
        "Linda Bennett",
        "https://images.unsplash.com/photo-1521146764736-56c929d59c83?w=500&h=500",
        "Linda Bennett's image"
    ),
    PetOwner(
        "Frances Garcia",
        "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=500&h=500",
        "Frances Garcia's image"
    ),
    PetOwner(
        "Rose Alexander",
        "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?w=500&h=500",
        "Rose Alexander's image"
    ),
)

val addresses = listOf(
    "586  Stout Street, Harrisburg, Pennsylvania",
    "2606  Williams Avenue, Newhall, California",
    "921  Ventura Drive, Santa Clara, California",
    "1016  Pride Avenue, Staten Island, New York",
    "4957  Lang Avenue, Vernon, Utah",
    "1979  Franklee Lane, LAS VEGAS, Nevada",
    "2656  Colonial Drive, Houston, Texas",
    "1041  Lake Road, Pleasantville, New Jersey",
    "2587  Benedum Drive, New York, New York",
)

val descriptions = listOf(
    "Not everything that is faced can be changed, but nothing can be changed until it is faced.",
    "It doesn't matter how strong your opinion are. If you don't use your power" +
        " for positive change, you are indeed part of the problem",
    "All great changes are preceded by choas",
    "We all get scared and want to turn away, but it isn't always strength that" +
        " makes you stay. Strength is also making the decision to change your destiny.",
    "We must be impatient for change. Let us remember that our voice is a precious" +
        " gift and we must use it",
    "If you do not change direction, you might end up where you are heading",
    "The moment of change is the only poem",
    "The only way to make sense out of change is to plunge into it, move with" +
        " it, and join the dance",
    "Change will not come if we wait for some other person or some other time." +
        " We are the ones we’ve been waiting for. We are the change that we seek",
)

val colors = listOf(
    "White",
    "Yellow",
    "Blue",
    "Red",
    "Green",
    "Black",
    "Brown",
    "Azure",
)

val allPets = listOf(
    Pet(
        0,
        "Charlie",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1601979031925-424e53b6caaa?w=500&h=500",
        "Charlie's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Max",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1530667912788-f976e8ee0bd5?w=500&h=500",
        "Max's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Buddy",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1583511655826-05700d52f4d9?w=500&h=500",
        "Buddy's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Oscar",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1559284957-298b8b225576?w=500&h=500",
        "Oscar's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Archie",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1537151672256-6caf2e9f8c95?w=500&h=500",
        "Archie's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Ollie",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1608787450139-538a6d3bb683?w=500&h=500",
        "Ollie's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Toby",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1548858565-461b87144b6a?w=500&h=500",
        "Toby's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Jack",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1548658146-f142deadf8f7?w=500&h=500",
        "Jack's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Teddy",
        PetCategory.Dog,
        "https://images.unsplash.com/photo-1599507303682-4055ce7235ae?w=500&h=500",
        "Teddy's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Luna",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1560114928-40f1f1eb26a0?w=500&h=500",
        "Luna's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Bella",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1591871937573-74dbba515c4c?w=500&h=500",
        "Bella's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Lucy",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?w=500&h=500",
        "Lucy's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Lily",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1603314585442-ee3b3c16fbcf?w=500&h=500",
        "Lily's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Chloe",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1568035105640-89538ccccd24?w=500&h=500",
        "Chloe's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Daisy",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1567270671170-fdc10a5bf831?w=500&h=500",
        "Daisy's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Mia",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1559059699-085698eba48c?w=500&h=500",
        "Mia's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Kiki",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1570419929578-07349d2138fa?w=500&h=500",
        "Kiki's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Zoe",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1589720247367-97b62ed5cfd1?w=500&h=500",
        "Zoe's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),
    Pet(
        0,
        "Piper",
        PetCategory.Cat,
        "https://images.unsplash.com/photo-1605530489666-6162f2cbd3b4?w=500&h=500",
        "Piper's photo",
        descriptions.random(),
        owners.random(),
        randomLikes(),
        addresses.random(),
        randomAge(),
        randomWeight(),
        colors.random()
    ),

).shuffled()
