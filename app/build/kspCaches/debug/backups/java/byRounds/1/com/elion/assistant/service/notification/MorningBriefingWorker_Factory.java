package com.elion.assistant.service.notification;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.elion.assistant.data.local.preferences.AppPreferences;
import com.elion.assistant.domain.repository.TaskRepository;
import com.elion.assistant.domain.usecase.analysis.GenerateMorningBriefingUseCase;
import com.elion.assistant.service.voice.TextToSpeechManager;
import dagger.internal.DaggerGenerated;
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
public final class MorningBriefingWorker_Factory {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<GenerateMorningBriefingUseCase> generateBriefingProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  private final Provider<AppPreferences> prefsProvider;

  private final Provider<TextToSpeechManager> ttsManagerProvider;

  public MorningBriefingWorker_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<GenerateMorningBriefingUseCase> generateBriefingProvider,
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<AppPreferences> prefsProvider, Provider<TextToSpeechManager> ttsManagerProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.generateBriefingProvider = generateBriefingProvider;
    this.notificationHelperProvider = notificationHelperProvider;
    this.prefsProvider = prefsProvider;
    this.ttsManagerProvider = ttsManagerProvider;
  }

  public MorningBriefingWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, taskRepositoryProvider.get(), generateBriefingProvider.get(), notificationHelperProvider.get(), prefsProvider.get(), ttsManagerProvider.get());
  }

  public static MorningBriefingWorker_Factory create(
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<GenerateMorningBriefingUseCase> generateBriefingProvider,
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<AppPreferences> prefsProvider, Provider<TextToSpeechManager> ttsManagerProvider) {
    return new MorningBriefingWorker_Factory(taskRepositoryProvider, generateBriefingProvider, notificationHelperProvider, prefsProvider, ttsManagerProvider);
  }

  public static MorningBriefingWorker newInstance(Context context, WorkerParameters params,
      TaskRepository taskRepository, GenerateMorningBriefingUseCase generateBriefing,
      NotificationHelper notificationHelper, AppPreferences prefs, TextToSpeechManager ttsManager) {
    return new MorningBriefingWorker(context, params, taskRepository, generateBriefing, notificationHelper, prefs, ttsManager);
  }
}
