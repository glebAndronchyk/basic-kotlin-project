package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lb1.ui.kit.Title
import com.example.lb1.ui.layouts.AppLayout

@Composable
fun StreamScreen(streamId: String, navController: NavHostController) {
    val streamEntity = mockStreams.find { streamDto ->  streamDto.id.toString() == streamId }

    if (streamEntity == null) {
        AppLayout(
            navController = navController,
            title = "Not Found",
            content = {}
        )
    } else {
        AppLayout(
            navController = navController,
            title = "Stream!!!",
            content = {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    AsyncImage(
                        model = streamEntity.imageUrl,
                        contentDescription = "Stream",
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )

                }
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    Title(text = streamEntity.name)
                }
            }
        )
    }
}
