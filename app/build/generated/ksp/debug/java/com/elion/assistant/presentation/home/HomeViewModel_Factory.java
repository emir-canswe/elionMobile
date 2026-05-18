package com.elion.assistant.presentation.home;

import com.elion.assistant.data.local.preferences.AppPreferences;
import com.elion.assistant.domain.repository.StatRepository;
import com.elion.assistant.domain.repository.TaskRepository;
import com.elion.assistant.domain.usecase.analysis.CheckPostponedTasksUseCase;
import com.elion.assistant.domain.usecase.analysis.GenerateMorningBriefingUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<StatRepository> statRepositoryProvider;

  private final Provider<CheckPostponedTasksUseCase> checkPostponedTasksProvider;

  private final Provider<GenerateMorningBriefingUseCase> generateMorningBriefingProvider;

  private final Provider<AppPreferences> prefsProvider;

  public HomeViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<StatRepository> statRepositoryProvider,
      Provider<CheckPostponedTasksUseCase> checkPostponedTasksProvider,
      Provider<GenerateMorningBriefingUseCase> generateMorningBriefingProvider,
      Provider<AppPreferences> prefsProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.statRepositoryProvider = statRepositoryProvider;
    this.checkPostponedTasksProvider = checkPostponedTasksProvider;
    this.generateMorningBriefingProvider = generateMorningBriefingProvider;
    this.prefsProvider = prefsProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(taskRepositoryProvider.get(), statRepositoryProvider.get(), checkPostponedTasksProvider.get(), generateMorningBriefingProvider.get(), prefsProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<StatRepository> statRepositoryProvider,
      Provider<CheckPostponedTasksUseCase> checkPostponedTasksProvider,
      Provider<GenerateMorningBriefingUseCase> generateMorningBriefingProvider,
      Provider<AppPreferences> prefsProvider) {
    return new HomeViewModel_Factory(taskRepositoryProvider, statRepositoryProvider, checkPostponedTasksProvider, generateMorningBriefingProvider, prefsProvider);
  }

  public static HomeViewModel newInstance(TaskRepository taskRepository,
      StatRepository statRepository, CheckPostponedTasksUseCase checkPostponedTasks,
      GenerateMorningBriefingUseCase generateMorningBriefing, AppPreferences prefs) {
    return new HomeViewModel(taskRepository, statRepository, checkPostponedTasks, generateMorningBriefing, prefs);
  }
}
