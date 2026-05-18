package com.elion.assistant.presentation.settings;

import com.elion.assistant.data.local.database.AppDatabase;
import com.elion.assistant.data.local.preferences.AppPreferences;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<AppPreferences> prefsProvider;

  private final Provider<AppDatabase> databaseProvider;

  public SettingsViewModel_Factory(Provider<AppPreferences> prefsProvider,
      Provider<AppDatabase> databaseProvider) {
    this.prefsProvider = prefsProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(prefsProvider.get(), databaseProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<AppPreferences> prefsProvider,
      Provider<AppDatabase> databaseProvider) {
    return new SettingsViewModel_Factory(prefsProvider, databaseProvider);
  }

  public static SettingsViewModel newInstance(AppPreferences prefs, AppDatabase database) {
    return new SettingsViewModel(prefs, database);
  }
}
