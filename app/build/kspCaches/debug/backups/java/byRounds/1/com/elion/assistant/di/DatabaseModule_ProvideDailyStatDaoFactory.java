package com.elion.assistant.di;

import com.elion.assistant.data.local.database.AppDatabase;
import com.elion.assistant.data.local.database.dao.DailyStatDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideDailyStatDaoFactory implements Factory<DailyStatDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideDailyStatDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DailyStatDao get() {
    return provideDailyStatDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDailyStatDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideDailyStatDaoFactory(dbProvider);
  }

  public static DailyStatDao provideDailyStatDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDailyStatDao(db));
  }
}
