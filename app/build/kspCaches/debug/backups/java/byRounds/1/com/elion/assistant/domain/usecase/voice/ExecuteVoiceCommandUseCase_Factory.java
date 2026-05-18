package com.elion.assistant.domain.usecase.voice;

import com.elion.assistant.domain.repository.TaskRepository;
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
public final class ExecuteVoiceCommandUseCase_Factory implements Factory<ExecuteVoiceCommandUseCase> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public ExecuteVoiceCommandUseCase_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public ExecuteVoiceCommandUseCase get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static ExecuteVoiceCommandUseCase_Factory create(
      Provider<TaskRepository> taskRepositoryProvider) {
    return new ExecuteVoiceCommandUseCase_Factory(taskRepositoryProvider);
  }

  public static ExecuteVoiceCommandUseCase newInstance(TaskRepository taskRepository) {
    return new ExecuteVoiceCommandUseCase(taskRepository);
  }
}
