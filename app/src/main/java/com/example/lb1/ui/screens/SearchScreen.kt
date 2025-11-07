package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lb1.ui.kit.Title
import com.example.lb1.ui.layouts.AppLayout
import com.example.lb1.ui.viewmodels.SearchViewModel

@Composable()
fun SearchScreen(searchViewModel: SearchViewModel = viewModel()) {
    val searchQuery by searchViewModel.searchQuery.collectAsState();
    val searchResults by searchViewModel.searchResults.collectAsState();

    AppLayout(title = "Search Games") {
        Row() {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchQuery,
                onValueChange = { newText -> searchViewModel.changeSearchQuery(newText) },
                placeholder = { Text("Service Games") }
            )
        }

        Row() {
            Column() {
                searchResults.forEach { game ->
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Title(text = game.name, modifier = Modifier.weight(1f))
                        Text(
                            text = game.description,
                            modifier = Modifier.weight(1f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}