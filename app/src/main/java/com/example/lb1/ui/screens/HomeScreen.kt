package com.example.lb1.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lb1.ui.kit.ButtonLabel
import com.example.lb1.ui.kit.Title
import com.example.lb1.ui.layouts.AppLayout

enum class HomeScreenTabs(
    val label: String,
) {
    ALL("All"),
    GAMES("Games")
}

@Composable
fun HomeScreen() {
    var currentTab by remember { mutableStateOf(HomeScreenTabs.ALL) }

    AppLayout(title = "Streams") {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(top = 36.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            HomeScreenTabs.entries.forEach { tab ->
                Button(
                    onClick = { currentTab = tab },
                ) {
                    ButtonLabel(tab.label)
                }
            }
        }

        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            when(currentTab) {
                HomeScreenTabs.ALL -> AllStreams()
                HomeScreenTabs.GAMES -> GamingStreams()
            }
        }
    }
}

@Composable
fun AllStreams() {
    Column() {
        Title(text = "All")
    }
}

@Composable
fun GamingStreams() {
    Column() {
        Title(text = "Games")
    }
}
