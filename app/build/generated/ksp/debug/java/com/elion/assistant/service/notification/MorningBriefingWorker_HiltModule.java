package com.elion.assistant.service.notification;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = MorningBriefingWorker.class
)
public interface MorningBriefingWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.elion.assistant.service.notification.MorningBriefingWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      MorningBriefingWorker_AssistedFactory factory);
}
