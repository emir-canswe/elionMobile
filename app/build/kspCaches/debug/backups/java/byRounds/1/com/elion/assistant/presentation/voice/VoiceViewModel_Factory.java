package com.elion.assistant.presentation.voice;

import com.elion.assistant.domain.usecase.voice.ExecuteVoiceCommandUseCase;
import com.elion.assistant.domain.usecase.voice.ParseVoiceCommandUseCase;
import com.elion.assistant.service.voice.SpeechRecognitionManager;
import com.elion.assistant.service.voice.TextToSpeechManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class VoiceViewModel_Factory implements Factory<VoiceViewModel> {
  private final Provider<SpeechRecognitionManager> speechRecognitionManagerProvider;

  private final Provider<TextToSpeechManager> ttsManagerProvider;

  private final Provider<ParseVoiceCommandUseCase> parseVoiceCommandUseCaseProvider;

  private final Provider<ExecuteVoiceCommandUseCase> executeVoiceCommandUseCaseProvider;

  public VoiceViewModel_Factory(Provider<SpeechRecognitionManager> speechRecognitionManagerProvider,
      Provider<TextToSpeechManager> ttsManagerProvider,
      Provider<ParseVoiceCommandUseCase> parseVoiceCommandUseCaseProvider,
      Provider<ExecuteVoiceCommandUseCase> executeVoiceCommandUseCaseProvider) {
    this.speechRecognitionManagerProvider = speechRecognitionManagerProvider;
    this.ttsManagerProvider = ttsManagerProvider;
    this.parseVoiceCommandUseCaseProvider = parseVoiceCommandUseCaseProvider;
    this.executeVoiceCommandUseCaseProvider = executeVoiceCommandUseCaseProvider;
  }

  @Override
  public VoiceViewModel get() {
    return newInstance(speechRecognitionManagerProvider.get(), ttsManagerProvider.get(), parseVoiceCommandUseCaseProvider.get(), executeVoiceCommandUseCaseProvider.get());
  }

  public static VoiceViewModel_Factory create(
      Provider<SpeechRecognitionManager> speechRecognitionManagerProvider,
      Provider<TextToSpeechManager> ttsManagerProvider,
      Provider<ParseVoiceCommandUseCase> parseVoiceCommandUseCaseProvider,
      Provider<ExecuteVoiceCommandUseCase> executeVoiceCommandUseCaseProvider) {
    return new VoiceViewModel_Factory(speechRecognitionManagerProvider, ttsManagerProvider, parseVoiceCommandUseCaseProvider, executeVoiceCommandUseCaseProvider);
  }

  public static VoiceViewModel newInstance(SpeechRecognitionManager speechRecognitionManager,
      TextToSpeechManager ttsManager, ParseVoiceCommandUseCase parseVoiceCommandUseCase,
      ExecuteVoiceCommandUseCase executeVoiceCommandUseCase) {
    return new VoiceViewModel(speechRecognitionManager, ttsManager, parseVoiceCommandUseCase, executeVoiceCommandUseCase);
  }
}
