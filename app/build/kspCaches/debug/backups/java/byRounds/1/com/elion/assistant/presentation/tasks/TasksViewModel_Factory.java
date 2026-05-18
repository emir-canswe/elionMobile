package com.elion.assistant.presentation.tasks;

import com.elion.assistant.domain.repository.CategoryRepository;
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
public final class TasksViewModel_Factory implements Factory<TasksViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  public TasksViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
  }

  @Override
  public TasksViewModel get() {
    return newInstance(taskRepositoryProvider.get(), categoryRepositoryProvider.get());
  }

  public static TasksViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider) {
    return new TasksViewModel_Factory(taskRepositoryProvider, categoryRepositoryProvider);
  }

  public static TasksViewModel newInstance(TaskRepository taskRepository,
      CategoryRepository categoryRepository) {
    return new TasksViewModel(taskRepository, categoryRepository);
  }
}
