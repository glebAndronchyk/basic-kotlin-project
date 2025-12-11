package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.lb1.ui.layouts.AppLayout

@Composable
fun WatchConfigureScreen(navController: NavHostController, onNarratorConfigure: () -> Unit) {
    AppLayout(
        title = "Configure streaming",
        navController = navController
    ) {
        Card( onClick = { onNarratorConfigure() }, modifier = Modifier.fillMaxWidth().height(120.dp).padding(12.dp).align(Alignment.CenterHorizontally)) {
            Row(
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "Narrator"
                )
            }
            Row(
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Narrator")
            }
        }
    }
}