package com.example.harry_potter_and_retrofit.presentation.ui.characterlistfragment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.harry_potter_and_retrofit.R
import com.example.harry_potter_and_retrofit.domain.model.CharacterItem
import com.example.harry_potter_and_retrofit.domain.model.CharacterPagingItem
import java.util.UUID
import kotlin.random.Random

@Composable
fun CharacterItemCompose(
    characterItem: CharacterItem? = null,
    characterPagingItem: CharacterPagingItem? = null,
) {


    if (characterItem != null) {
        CharacterCard(characterItem)
    } else {
        val characterMapped = CharacterItem(
            id = Random.nextInt(),
            character = characterPagingItem?.name ?: "N/D",
            hogwartsHouse = characterPagingItem?.hogwartsHouse ?: "N/D",
            image = characterPagingItem?.imageUrl ?: "empty"
        )
        CharacterCard(characterMapped)

    }


}

@Composable
private fun CharacterCard(characterItem: CharacterItem) {
    Card(
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background_main)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            SubcomposeAsyncImage(
                model = if (characterItem.image != null) characterItem.image else R.drawable.ic_face,
                contentDescription = "Character image",
                loading = { CircularProgressIndicator() },
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(100)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(characterItem.character, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(3.dp))
                Text(characterItem.hogwartsHouse, fontSize = 15.sp)
            }
        }
    }
}

