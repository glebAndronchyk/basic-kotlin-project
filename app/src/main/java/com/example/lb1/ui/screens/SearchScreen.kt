package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

    AppLayout(title = "Search Games", rightAdornment = {
        IconButton(onClick = { searchViewModel.saveUniqueToDb() }) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Save"
            )
        }
    }) {
        Row() {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchQuery,
                onValueChange = { newText -> searchViewModel.changeSearchQuery(newText) },
                placeholder = { Text("Service Games") }
            )
        }

        Row() {
            LazyColumn ( modifier = Modifier.fillMaxSize() ) {
                items(searchResults) { result ->
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Title(text = result.name, modifier = Modifier.weight(1f))
                        Text(
                            text = result.description,
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