package com.elion.assistant;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ElionApplication_MembersInjector implements MembersInjector<ElionApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public ElionApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<ElionApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new ElionApplication_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(ElionApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.elion.assistant.ElionApplication.workerFactory")
  public static void injectWorkerFactory(ElionApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
