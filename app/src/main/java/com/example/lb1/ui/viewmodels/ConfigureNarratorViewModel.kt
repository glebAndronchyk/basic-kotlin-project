package com.example.lb1.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class CreateFormData(
    val avatar: String = "",
    val name: String = ""
)

data class UpdateFormData(
    val id: Long = 0,
    val avatar: String = "",
    val name: String = ""
)

data class DeleteFormData(
    val id: Long = 0
)

class ConfigureNarratorViewModel : ViewModel() {
    val createFormData: MutableState<CreateFormData> = mutableStateOf(CreateFormData())
    val updateFormData: MutableState<UpdateFormData> = mutableStateOf(UpdateFormData())
    val deleteFormData: MutableState<DeleteFormData> = mutableStateOf(DeleteFormData())

    fun changeUpdateId(id: Long) {
        updateFormData.value = updateFormData.value.copy(id=id);
    }

    fun changeUpdateAvatar(avatar: String) {
        updateFormData.value = updateFormData.value.copy(avatar=avatar);
    }

    fun changeUpdateName(name: String) {
        updateFormData.value = updateFormData.value.copy(name=name);
    }

    fun changeCreateName(name: String) {
        createFormData.value = createFormData.value.copy(name=name);
    }

    fun changeCreateAvatar(avatar: String) {
        createFormData.value = createFormData.value.copy(avatar=avatar);
    }

    fun changeDeleteId(id: Long) {
        deleteFormData.value = deleteFormData.value.copy(id=id);
    }

}