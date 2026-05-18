package com.elion.assistant.domain.usecase.analysis;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CheckPostponedTasksUseCase_Factory implements Factory<CheckPostponedTasksUseCase> {
  @Override
  public CheckPostponedTasksUseCase get() {
    return newInstance();
  }

  public static CheckPostponedTasksUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CheckPostponedTasksUseCase newInstance() {
    return new CheckPostponedTasksUseCase();
  }

  private static final class InstanceHolder {
    private static final CheckPostponedTasksUseCase_Factory INSTANCE = new CheckPostponedTasksUseCase_Factory();
  }
}
