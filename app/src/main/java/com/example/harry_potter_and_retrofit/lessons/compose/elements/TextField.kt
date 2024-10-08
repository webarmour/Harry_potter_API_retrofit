package com.example.harry_potter_and_retrofit.lessons.compose.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.room.util.TableInfo
import com.example.harry_potter_and_retrofit.R


@Composable
fun TextField() {

    val textValue = rememberSaveable { mutableStateOf("") }

    val primaryColor = colorResource(id = R.color.main)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = textValue.value, onValueChange = {
            textValue.value = it
        },
            label = {Text(text = "Email")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = primaryColor,
                focusedIndicatorColor = primaryColor,
                focusedLabelColor = primaryColor,
                cursorColor = primaryColor
            )
        )

    }

}