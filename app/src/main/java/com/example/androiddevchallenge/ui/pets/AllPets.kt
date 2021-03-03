package com.example.androiddevchallenge.ui.pets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.PetCategory
import com.example.androiddevchallenge.model.allPets
import com.example.androiddevchallenge.model.randomAmount
import com.example.androiddevchallenge.ui.common.LikeButton
import com.example.androiddevchallenge.ui.common.LocalRoundAvatar
import com.example.androiddevchallenge.ui.common.OutlinedAvatar
import com.example.androiddevchallenge.ui.common.StaggeredVerticalGrid
import com.example.androiddevchallenge.ui.theme.PetsTheme
import com.example.androiddevchallenge.ui.utils.NetworkImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun AllPets(
    modifier: Modifier = Modifier,
    pets: List<Pet> = allPets,
    lovePet: (Long) -> Unit = {},
    adoptPet: (Long) -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {
        PetsAppBar()
        Header()
        Categories {}
        NewestPets(pets,adoptPet)

        Spacer(modifier = Modifier.height(100.dp))
    }

}

@Composable
fun NewestPets(
    pets: List<Pet>,
    selectPet: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Newest Pets", style = MaterialTheme.typography.h6)
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            pets.forEach {
                PetCard(Modifier, it, selectPet)
            }
        }
    }
}

@Composable
fun PetCard(
    modifier: Modifier,
    pet: Pet,
    selectPet: (Long) -> Unit
) {
    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = PetsTheme.elevations.card,
        shape = MaterialTheme.shapes.medium
    ) {
        val featuredString = stringResource(id = R.string.favorite)
        ConstraintLayout(
            modifier = Modifier
                .clickable(
                    onClick = { selectPet(pet.id) }
                )
                .semantics {
                    contentDescription = featuredString
                }
        ) {
            val (image, like, avatar, name, address) = createRefs()
            NetworkImage(
                url = pet.thumbUrl,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(4f / 3f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )
            val outlineColor = LocalElevationOverlay.current?.apply(
                color = MaterialTheme.colors.surface,
                elevation = PetsTheme.elevations.card
            ) ?: MaterialTheme.colors.surface

            LikeButton(
                modifier = Modifier
                    .constrainAs(like) {
                        top.linkTo(parent.top, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
            )

            OutlinedAvatar(
                url = pet.owner.thumbUrl,
                outlineColor = outlineColor,
                modifier = Modifier
                    .size(38.dp)
                    .constrainAs(avatar) {
                        centerHorizontallyTo(parent)
                        centerAround(image.bottom)
                    }
            )
            Text(
                text = pet.name,
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(name) {
                        start.linkTo(parent.start)
                        top.linkTo(avatar.bottom)
                    }
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .constrainAs(address) {
                        start.linkTo(parent.start)
                        top.linkTo(name.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = pet.address,
                    tint = PetsTheme.colors.onSurface.copy(.5F)
                )
                Text(
                    text = pet.address,
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }

}


@Composable
fun Categories(
    selectCategory: (PetCategory) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Pet Category", style = MaterialTheme.typography.h6)
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            PetCategory::class.sealedSubclasses.map { it.objectInstance as PetCategory }
                .forEach { PetCategoryCard(it, selectCategory) }
        }
    }
}

@Composable
fun PetCategoryCard(
    petCategory: PetCategory,
    selectCategory: (PetCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {}),
        color = MaterialTheme.colors.surface.copy(.15F),
        elevation = PetsTheme.elevations.card,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray.copy(.12F))
    ) {
        Row(modifier = Modifier.padding(start = 16.dp)) {
            LocalRoundAvatar(
                petCategory.icon,
                PetsTheme.colors.surface.copy(.5F),
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = petCategory.name),
                    style = MaterialTheme.typography.subtitle2
                )
                Text(text = "Total of ${randomAmount()}", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    var textFieldValue = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Text(
            stringResource(R.string.find_your),
            style = MaterialTheme.typography.h6,
        )
        Text(
            stringResource(R.string.subtitle),
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(Modifier.height(16.dp))

        TextField(
            value = textFieldValue.value,
            onValueChange = { value -> textFieldValue.value = value },
            Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = PetsTheme.colors.onPrimary
                )
            },
            placeholder = { Text("Search", color = PetsTheme.colors.onPrimary) },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Black.copy(alpha = 0.05F),
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colors.onPrimary,
                leadingIconColor = MaterialTheme.colors.primary
            ),
            textStyle = TextStyle(fontSize = 20.sp)
        )

    }
}