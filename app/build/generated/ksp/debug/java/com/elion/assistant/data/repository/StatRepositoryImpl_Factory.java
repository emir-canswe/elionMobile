package com.elion.assistant.data.repository;

import com.elion.assistant.data.local.database.dao.DailyStatDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class StatRepositoryImpl_Factory implements Factory<StatRepositoryImpl> {
  private final Provider<DailyStatDao> dailyStatDaoProvider;

  public StatRepositoryImpl_Factory(Provider<DailyStatDao> dailyStatDaoProvider) {
    this.dailyStatDaoProvider = dailyStatDaoProvider;
  }

  @Override
  public StatRepositoryImpl get() {
    return newInstance(dailyStatDaoProvider.get());
  }

  public static StatRepositoryImpl_Factory create(Provider<DailyStatDao> dailyStatDaoProvider) {
    return new StatRepositoryImpl_Factory(dailyStatDaoProvider);
  }

  public static StatRepositoryImpl newInstance(DailyStatDao dailyStatDao) {
    return new StatRepositoryImpl(dailyStatDao);
  }
}
