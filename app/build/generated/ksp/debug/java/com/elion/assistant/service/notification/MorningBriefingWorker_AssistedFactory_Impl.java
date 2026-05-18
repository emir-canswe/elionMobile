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
public final class MorningBriefingWorker_AssistedFactory_Impl implements MorningBriefingWorker_AssistedFactory {
  private final MorningBriefingWorker_Factory delegateFactory;

  MorningBriefingWorker_AssistedFactory_Impl(MorningBriefingWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public MorningBriefingWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<MorningBriefingWorker_AssistedFactory> create(
      MorningBriefingWorker_Factory delegateFactory) {
    return InstanceFactory.create(new MorningBriefingWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<MorningBriefingWorker_AssistedFactory> createFactoryProvider(
      MorningBriefingWorker_Factory delegateFactory) {
    return InstanceFactory.create(new MorningBriefingWorker_AssistedFactory_Impl(delegateFactory));
  }
}
