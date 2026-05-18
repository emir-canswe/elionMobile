package com.elion.assistant.presentation.stats;

import com.elion.assistant.domain.repository.StatRepository;
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
public final class StatsViewModel_Factory implements Factory<StatsViewModel> {
  private final Provider<StatRepository> statRepositoryProvider;

  public StatsViewModel_Factory(Provider<StatRepository> statRepositoryProvider) {
    this.statRepositoryProvider = statRepositoryProvider;
  }

  @Override
  public StatsViewModel get() {
    return newInstance(statRepositoryProvider.get());
  }

  public static StatsViewModel_Factory create(Provider<StatRepository> statRepositoryProvider) {
    return new StatsViewModel_Factory(statRepositoryProvider);
  }

  public static StatsViewModel newInstance(StatRepository statRepository) {
    return new StatsViewModel(statRepository);
  }
}
