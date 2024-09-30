package com.example.harry_potter_and_retrofit.presentation.ui.mainfragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem

@Composable
fun MainFragmentCompose(viewModel: MainViewModel) {

    val character by viewModel.character.collectAsState()

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize() ){
        Image(
            painter = painterResource(id = R.drawable.image_castle),
            contentDescription = "Image castle",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        MainColumn(character, viewModel)
    }



}

@Composable
private fun MainColumn(
    character: CharacterItem,
    viewModel: MainViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(50.dp)
    ) {
        SubcomposeAsyncImage(
            model = character.image,
            contentDescription = "Image of character",
            loading = {
                CircularProgressIndicator(color = Color.White, strokeWidth = 8.dp)
            },
            modifier = Modifier
                .size(200.dp, 250.dp)
                .border(3.dp, color = Color.LightGray),
            contentScale = ContentScale.FillBounds
        )
        Text(
            character.character,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(character.hogwartsHouse, color = Color.White, fontSize = 20.sp)
        OutlinedButton(onClick = { viewModel.getRandomCharacter() }, ) {
            Text(text = stringResource(id = R.string.random), color = Color.LightGray)
        }
    }
}