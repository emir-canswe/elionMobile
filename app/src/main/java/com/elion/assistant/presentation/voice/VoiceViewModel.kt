package com.elion.assistant.presentation.voice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elion.assistant.domain.usecase.voice.ExecuteVoiceCommandUseCase
import com.elion.assistant.domain.usecase.voice.ParseVoiceCommandUseCase
import com.elion.assistant.service.voice.SpeechRecognitionManager
import com.elion.assistant.service.voice.SpeechState
import com.elion.assistant.service.voice.TextToSpeechManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VoiceUiState(
    val isListening: Boolean = false,
    val text: String = "",
    val feedback: String = ""
)

@HiltViewModel
class VoiceViewModel @Inject constructor(
    private val speechRecognitionManager: SpeechRecognitionManager,
    private val ttsManager: TextToSpeechManager,
    private val parseVoiceCommandUseCase: ParseVoiceCommandUseCase,
    private val executeVoiceCommandUseCase: ExecuteVoiceCommandUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(VoiceUiState())
    val uiState: StateFlow<VoiceUiState> = _uiState.asStateFlow()

    init {
        ttsManager.init()
        viewModelScope.launch {
            speechRecognitionManager.state.collect { state ->
                when (state) {
                    is SpeechState.Idle -> _uiState.value = _uiState.value.copy(isListening = false)
                    is SpeechState.Listening -> _uiState.value = _uiState.value.copy(isListening = true, feedback = "Sizi dinliyorum...")
                    is SpeechState.Processing -> _uiState.value = _uiState.value.copy(isListening = false, feedback = "İşleniyor...")
                    is SpeechState.Done -> {
                        _uiState.value = _uiState.value.copy(isListening = false, text = state.text)
                        processCommand(state.text)
                    }
                    is SpeechState.Error -> {
                        _uiState.value = _uiState.value.copy(isListening = false, feedback = state.message)
                        ttsManager.speak(state.message)
                        speechRecognitionManager.reset()
                    }
                }
            }
        }
        viewModelScope.launch {
            speechRecognitionManager.result.collect { text ->
                if (text != null) {
                    _uiState.value = _uiState.value.copy(text = text)
                }
            }
        }
    }

    fun startListening() {
        speechRecognitionManager.startListening()
    }

    fun stopListening() {
        speechRecognitionManager.stopListening()
    }

    private fun processCommand(text: String) {
        viewModelScope.launch {
            val intent = parseVoiceCommandUseCase(text)
            val resultMessage = executeVoiceCommandUseCase(intent)
            _uiState.value = _uiState.value.copy(feedback = resultMessage)
            ttsManager.speak(resultMessage)
        }
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognitionManager.destroy()
        ttsManager.destroy()
    }
}
