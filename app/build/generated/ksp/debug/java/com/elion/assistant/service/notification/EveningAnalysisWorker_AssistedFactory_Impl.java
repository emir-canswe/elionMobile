package com.elion.assistant.service.notification;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class EveningAnalysisWorker_AssistedFactory_Impl implements EveningAnalysisWorker_AssistedFactory {
  private final EveningAnalysisWorker_Factory delegateFactory;

  EveningAnalysisWorker_AssistedFactory_Impl(EveningAnalysisWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public EveningAnalysisWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<EveningAnalysisWorker_AssistedFactory> create(
      EveningAnalysisWorker_Factory delegateFactory) {
    return InstanceFactory.create(new EveningAnalysisWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<EveningAnalysisWorker_AssistedFactory> createFactoryProvider(
      EveningAnalysisWorker_Factory delegateFactory) {
    return InstanceFactory.create(new EveningAnalysisWorker_AssistedFactory_Impl(delegateFactory));
  }
}
