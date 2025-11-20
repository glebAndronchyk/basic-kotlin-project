package com.example.lb1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lb1.ui.kit.Title
import com.example.lb1.ui.layouts.AppLayout
import com.example.lb1.ui.theme.Purple80

data class StreamDto(
    val id: Int,
    val name: String,
    val imageUrl: String
)

val mockStreams = listOf(
    StreamDto(
        id = 0,
        name = "xQc",
        imageUrl = "https://picsum.photos/400/400?random=1"
    ),
    StreamDto(
        id = 1,
        name = "Ninja",
        imageUrl = "https://picsum.photos/400/400?random=2"
    ),
)

@Composable()
fun WatchScreen(onStreamClick: (String) -> Unit) {
    var selectedStreamId by remember { mutableIntStateOf(0) }

    AppLayout(title = "Streams") {
        mockStreams.forEach { stream ->
            Row(
                modifier = Modifier
                    .then(if (selectedStreamId == stream.id) Modifier.background(Purple80) else Modifier.background(Color(0xFFF)))
                    .clickable(enabled = true, onClick = {
                        selectedStreamId = stream.id
                        onStreamClick(stream.id.toString());
                    })
            ) {
                AsyncImage(
                    model = stream.imageUrl,
                    contentDescription = "Game cover",
                    modifier = Modifier.size(60.dp)
                )
                Column() {
                    Title(text=stream.name)
                }
            }
        }
    }
}
