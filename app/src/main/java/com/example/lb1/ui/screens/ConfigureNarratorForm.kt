package com.example.lb1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lb1.domain.model.CreateNarratorDto
import com.example.lb1.domain.model.UpdateNarratorDto
import com.example.lb1.ui.kit.Title
import com.example.lb1.ui.layouts.AppLayout
import com.example.lb1.ui.viewmodels.ConfigureNarratorViewModel

@Composable
fun ConfigureNarratorForm(
    navController: NavHostController = rememberNavController(),
    viewmodel: ConfigureNarratorViewModel = viewModel(),
    onCreate: (narrator: CreateNarratorDto) -> Unit,
    onDelete: (id: Number) -> Unit,
    onUpdate: (narrator: UpdateNarratorDto) -> Unit,
) {
    val createFormData by viewmodel.createFormData;
    val updateFormData by viewmodel.updateFormData;
    val deleteFormData by viewmodel.deleteFormData;

    AppLayout("Configure Narrator", navController = navController) {
        Column(modifier = Modifier.padding(16.dp)) {
            Title("Create")
            TextField(
                onValueChange = { value -> viewmodel.changeCreateName(value) },
                value = createFormData.name,
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                onValueChange = { value -> viewmodel.changeCreateAvatar(value) },
                value = createFormData.avatar,
                label = { Text("Avatar URL") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                onCreate(CreateNarratorDto(
                    name = createFormData.name,
                    avatar = createFormData.avatar
                ))
            }) {
                Text("Create")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Title("Update")
            TextField(
                onValueChange = { value -> viewmodel.changeUpdateId(value.toLongOrNull() ?: 0) },
                value = updateFormData.id.toString(),
                label = { Text("ID") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                onValueChange = { value -> viewmodel.changeUpdateName(value) },
                value = updateFormData.name,
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                onValueChange = { value -> viewmodel.changeUpdateAvatar(value) },
                value = updateFormData.avatar,
                label = { Text("Avatar URL") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                onUpdate(UpdateNarratorDto(
                    id = updateFormData.id,
                    name = updateFormData.name,
                    avatar = updateFormData.avatar
                ))
            }) {
                Text("Update")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Title("Delete")
            TextField(
                onValueChange = { value -> viewmodel.changeDeleteId(value.toLongOrNull() ?: 0) },
                value = deleteFormData.id.toString(),
                label = { Text("ID") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                onDelete(deleteFormData.id)
            }) {
                Text("Delete")
            }
        }
    }
}