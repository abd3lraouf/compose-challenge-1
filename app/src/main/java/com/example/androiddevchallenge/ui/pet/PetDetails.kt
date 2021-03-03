package com.example.androiddevchallenge.ui.pet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.PetRepo
import com.example.androiddevchallenge.ui.common.LikeButton
import com.example.androiddevchallenge.ui.common.OutlinedAvatar
import com.example.androiddevchallenge.ui.theme.PetsTheme
import com.example.androiddevchallenge.ui.utils.NetworkImage
import com.example.androiddevchallenge.ui.utils.scrim
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun PetDetails(
    petId: Long,
    lovePet: (Long) -> Unit,
    adoptPet: (Long) -> Unit,
    upPress: () -> Unit
) {
    val pet = remember(petId) { PetRepo.getPet(petId) }
    PetDetails(pet, lovePet, adoptPet, upPress)
}

@Composable
fun PetDetails(pet: Pet, lovePet: (Long) -> Unit, adoptPet: (Long) -> Unit, upPress: () -> Unit) {
    PetsTheme {
        Column(Modifier) {
            Box {
                NetworkImage(
                    url = pet.thumbUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scrim(colors = listOf(Color(0x80000000), Color(0x33000000)))
                        .aspectRatio(4f / 3f)
                )
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    contentColor = Color.White, // always white as image has dark scrim
                    modifier = Modifier.statusBarsPadding()
                ) {
                    IconButton(onClick = upPress) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.label_back)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.adopt),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            ConstraintLayout {
                val (name, addressIcon, address, like, age, color, weight, petStory, petStoryText, ownerAvatar, ownerPostedBy, ownerName, contactMe) = createRefs()
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 16.dp)
                        }

                )

                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = pet.address,
                    tint = PetsTheme.colors.onSurface.copy(.5F),
                    modifier = Modifier.constrainAs(addressIcon) {
                        top.linkTo(name.bottom, margin = 16.dp)
                        start.linkTo(name.start)
                    }
                )

                Text(
                    text = pet.address,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.constrainAs(address) {
                        top.linkTo(addressIcon.top)
                        bottom.linkTo(addressIcon.bottom)
                        start.linkTo(addressIcon.end, margin = 16.dp)
                    }

                )

                LikeButton(
                    modifier = Modifier
                        .constrainAs(like) {
                            top.linkTo(name.top)
                            bottom.linkTo(addressIcon.bottom)
                            end.linkTo(parent.end, margin = 16.dp)

                        }
                )

                PetDetailsCategoryCard("Age", "${pet.age} months",
                    modifier = Modifier
                        .constrainAs(age) {
                            top.linkTo(addressIcon.bottom, margin = 16.dp)
                            start.linkTo(addressIcon.start)
                        }
                )
                PetDetailsCategoryCard("Color", pet.color,
                    modifier = Modifier
                        .constrainAs(color) {
                            top.linkTo(addressIcon.bottom, margin = 16.dp)
                            start.linkTo(age.end, margin = 4.dp)
                        }
                )
                PetDetailsCategoryCard("Weight", "${pet.weight.toInt()} KG",
                    modifier = Modifier
                        .constrainAs(weight) {
                            top.linkTo(addressIcon.bottom, margin = 16.dp)
                            start.linkTo(color.end, margin = 4.dp)
                        }
                )

                Text(
                    text = "Pet Story",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(petStory) {
                            top.linkTo(age.bottom, margin = 24.dp)
                            start.linkTo(age.start)
                        }
                )

                Text(
                    text = pet.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(petStoryText) {
                            top.linkTo(petStory.bottom, margin = 16.dp)
                            start.linkTo(petStory.start)
                        }
                )

                OutlinedAvatar(
                    url = pet.owner.thumbUrl,
                    modifier = Modifier
                        .size(40.dp)
                        .constrainAs(ownerAvatar) {
                            top.linkTo(petStoryText.bottom, margin = 32.dp)
                            start.linkTo(petStory.start)
                        }
                )

                Text(
                    text = "Posted by",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(ownerPostedBy) {
                            top.linkTo(ownerAvatar.top)
                            start.linkTo(ownerAvatar.end, margin = 16.dp)
                        }
                )

                Text(
                    text = pet.owner.name,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(ownerName) {
                            top.linkTo(ownerPostedBy.bottom)
                            bottom.linkTo(ownerAvatar.bottom)
                            start.linkTo(ownerPostedBy.start)
                        }
                )

                Button(onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .constrainAs(contactMe) {
                            top.linkTo(ownerAvatar.top)
                            bottom.linkTo(ownerAvatar.bottom)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Contact ${pet.owner.name}")
                    Text(modifier = Modifier.padding(start = 10.dp),text = "Contact me")
                }

            }
        }
    }
}

@Composable
fun PetDetailsCategoryCard(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .wrapContentSize()
            .clip(RoundedCornerShape(8.dp)),
        color = MaterialTheme.colors.surface.copy(.15F),
        elevation = PetsTheme.elevations.card,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray.copy(.12F))
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}
