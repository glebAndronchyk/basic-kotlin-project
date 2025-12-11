package com.example.lb1.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.lb1.ui.kit.Title
import com.example.lb1.ui.layouts.AppLayout
import com.example.lb1.ui.presentations.ClipPresentation
import com.example.lb1.ui.presentations.NarratorPresentation
import com.example.lb1.ui.presentations.StreamPresentation
import com.example.lb1.ui.viewmodels.StreamsViewModel

sealed interface WatchItem {
    data class NarratorsSection(val narrators: List<NarratorPresentation>): WatchItem
    data class StreamItem(val stream: StreamPresentation): WatchItem
    data class ClipItem(val clip: ClipPresentation): WatchItem
    data class Title(val title: String): WatchItem
}

@Composable
fun NarratorsList(narrators: List<NarratorPresentation>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(narrators) { narrator ->
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(110.dp)
                    .clickable { /* Handle narrator click */ }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = narrator.avatar,
                        contentDescription = narrator.name,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = narrator.name,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@Composable
fun ClipComposable(clip: ClipPresentation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = clip.previewUrl,
                contentDescription = clip.name,
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = clip.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = clip.narratorName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "• ${clip.duration}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Text(
                    text = clip.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun StreamComposable(stream: StreamPresentation,  onStreamClick: (Number) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onStreamClick(stream.id) }
            .border(1.dp, Color.Green)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = stream.previewUrl,
                contentDescription = stream.name,
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stream.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stream.narratorName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Text(
                    text = stream.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun SettingsButton(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Settings"
        )
    }
}


@Composable
fun WatchScreen(
    navController: NavHostController = rememberNavController(),
    onSettingsClick: () -> Unit,
    onStreamClick: (Number) -> Unit,
    viewModel: StreamsViewModel = viewModel()
) {
    val streams by viewModel.streams.collectAsState()
    val clips by viewModel.clips.collectAsState()
    val narrators by viewModel.narrators.collectAsState()

    val combinedItems = buildList {
        add(WatchItem.Title("Narrators"))
        if (narrators.isEmpty()) {
            add(WatchItem.Title("No narrators found"))
        } else {
            add(WatchItem.NarratorsSection(narrators))
        }

        add(WatchItem.Title("Streams"))
        if (streams.isEmpty()) {
            add(WatchItem.Title("No streams found"))
        } else {
            addAll(streams.map {  WatchItem.StreamItem(it) })
        }

        add(WatchItem.Title("Clips"))
        if (clips.isEmpty()) {
            add(WatchItem.Title("No clips found"))
        } else {
            addAll(clips.map {  WatchItem.ClipItem(it) })
        }
    }

    AppLayout(title = "Watch", navController = navController, rightAdornment = {
        SettingsButton(onClick = { onSettingsClick() })
    }) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(combinedItems) { item ->
                when(item) {
                    is WatchItem.Title -> Title(item.title)
                    is WatchItem.NarratorsSection -> NarratorsList(item.narrators)
                    is WatchItem.ClipItem -> ClipComposable(item.clip)
                    is WatchItem.StreamItem -> StreamComposable(item.stream, onStreamClick)
                }
            }
        }
    }
}
