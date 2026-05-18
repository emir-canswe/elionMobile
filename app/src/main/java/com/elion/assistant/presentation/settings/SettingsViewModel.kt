package com.elion.assistant.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elion.assistant.data.local.database.AppDatabase
import com.elion.assistant.data.local.preferences.AppPreferences
import com.elion.assistant.domain.model.Category
import com.elion.assistant.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val assistantName: String = "ELION",
    val morningHour: Int = 8,
    val morningMinute: Int = 0,
    val eveningHour: Int = 21,
    val eveningMinute: Int = 0,
    val isTtsEnabled: Boolean = true,
    val isNotificationSoundEnabled: Boolean = true,
    val commentTone: Float = 0f
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val prefs: AppPreferences,
    private val database: AppDatabase,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val timeFlow = combine(
        prefs.morningHour, prefs.morningMinute, prefs.eveningHour, prefs.eveningMinute
    ) { mh, mm, eh, em ->
        listOf(mh, mm, eh, em)
    }

    val uiState: StateFlow<SettingsUiState> = combine(
        prefs.assistantName,
        timeFlow,
        prefs.ttsEnabled,
        prefs.notificationSound,
        prefs.commentTone
    ) { name, times, tts, sound, tone ->
        SettingsUiState(
            assistantName = name,
            morningHour = times[0],
            morningMinute = times[1],
            eveningHour = times[2],
            eveningMinute = times[3],
            isTtsEnabled = tts,
            isNotificationSoundEnabled = sound,
            commentTone = tone
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsUiState())

    val categories: StateFlow<List<Category>> = categoryRepository.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateAssistantName(name: String) {
        viewModelScope.launch { prefs.setAssistantName(name) }
    }

    fun updateMorningTime(hour: Int, minute: Int) {
        viewModelScope.launch { prefs.setMorningTime(hour, minute) }
    }

    fun updateEveningTime(hour: Int, minute: Int) {
        viewModelScope.launch { prefs.setEveningTime(hour, minute) }
    }

    fun updateTtsEnabled(enabled: Boolean) {
        viewModelScope.launch { prefs.setTtsEnabled(enabled) }
    }

    fun updateNotificationSound(enabled: Boolean) {
        viewModelScope.launch { prefs.setNotificationSound(enabled) }
    }

    fun updateCommentTone(tone: Float) {
        viewModelScope.launch { prefs.setCommentTone(tone) }
    }

    fun addCategory(name: String, colorHex: String) {
        viewModelScope.launch {
            categoryRepository.insertCategory(
                Category(
                    name = name,
                    colorHex = colorHex,
                    iconName = "label",
                    isDefault = false,
                    sortOrder = 10
                )
            )
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.updateCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.deleteCategory(category)
        }
    }

    fun resetAllData() {
        viewModelScope.launch {
            database.clearAllTables()
            prefs.clearAll()
            categoryRepository.seedDefaultCategories()
        }
    }
}
