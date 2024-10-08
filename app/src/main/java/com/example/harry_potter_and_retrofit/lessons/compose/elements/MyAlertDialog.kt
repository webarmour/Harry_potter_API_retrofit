package com.example.harry_potter_and_retrofit.lessons.compose.elements

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun MyAlertDialog() {

    val shouldShowDialog = remember { mutableStateOf(true) }

    if (shouldShowDialog.value){
        AlertDialog(onDismissRequest = { 
            shouldShowDialog.value = false
        },
            title = { Text(text = "Title of AlertDialog")},
            text = { Text(text = "Content of AlertDialog")},
            confirmButton = {
                Button(onClick = { 
                    shouldShowDialog.value = false
                }) {
                    Text(text = "OK")
                }
                
            })
    }

}