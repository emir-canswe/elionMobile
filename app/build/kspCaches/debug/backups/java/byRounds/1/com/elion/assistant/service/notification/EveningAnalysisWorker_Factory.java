package com.elion.assistant.service.notification;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.elion.assistant.data.local.preferences.AppPreferences;
import com.elion.assistant.domain.repository.StatRepository;
import com.elion.assistant.domain.repository.TaskRepository;
import com.elion.assistant.domain.usecase.analysis.GenerateEveningAnalysisUseCase;
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
public final class EveningAnalysisWorker_Factory {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<StatRepository> statRepositoryProvider;

  private final Provider<GenerateEveningAnalysisUseCase> generateAnalysisProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  private final Provider<AppPreferences> prefsProvider;

  private final Provider<TextToSpeechManager> ttsManagerProvider;

  public EveningAnalysisWorker_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<StatRepository> statRepositoryProvider,
      Provider<GenerateEveningAnalysisUseCase> generateAnalysisProvider,
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<AppPreferences> prefsProvider, Provider<TextToSpeechManager> ttsManagerProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.statRepositoryProvider = statRepositoryProvider;
    this.generateAnalysisProvider = generateAnalysisProvider;
    this.notificationHelperProvider = notificationHelperProvider;
    this.prefsProvider = prefsProvider;
    this.ttsManagerProvider = ttsManagerProvider;
  }

  public EveningAnalysisWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, taskRepositoryProvider.get(), statRepositoryProvider.get(), generateAnalysisProvider.get(), notificationHelperProvider.get(), prefsProvider.get(), ttsManagerProvider.get());
  }

  public static EveningAnalysisWorker_Factory create(
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<StatRepository> statRepositoryProvider,
      Provider<GenerateEveningAnalysisUseCase> generateAnalysisProvider,
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<AppPreferences> prefsProvider, Provider<TextToSpeechManager> ttsManagerProvider) {
    return new EveningAnalysisWorker_Factory(taskRepositoryProvider, statRepositoryProvider, generateAnalysisProvider, notificationHelperProvider, prefsProvider, ttsManagerProvider);
  }

  public static EveningAnalysisWorker newInstance(Context context, WorkerParameters params,
      TaskRepository taskRepository, StatRepository statRepository,
      GenerateEveningAnalysisUseCase generateAnalysis, NotificationHelper notificationHelper,
      AppPreferences prefs, TextToSpeechManager ttsManager) {
    return new EveningAnalysisWorker(context, params, taskRepository, statRepository, generateAnalysis, notificationHelper, prefs, ttsManager);
  }
}
