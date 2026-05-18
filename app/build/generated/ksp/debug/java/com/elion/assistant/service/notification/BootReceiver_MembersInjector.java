package com.elion.assistant.service.notification;

import com.elion.assistant.data.local.preferences.AppPreferences;
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
public final class BootReceiver_MembersInjector implements MembersInjector<BootReceiver> {
  private final Provider<AppPreferences> prefsProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  public BootReceiver_MembersInjector(Provider<AppPreferences> prefsProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    this.prefsProvider = prefsProvider;
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public static MembersInjector<BootReceiver> create(Provider<AppPreferences> prefsProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    return new BootReceiver_MembersInjector(prefsProvider, notificationHelperProvider);
  }

  @Override
  public void injectMembers(BootReceiver instance) {
    injectPrefs(instance, prefsProvider.get());
    injectNotificationHelper(instance, notificationHelperProvider.get());
  }

  @InjectedFieldSignature("com.elion.assistant.service.notification.BootReceiver.prefs")
  public static void injectPrefs(BootReceiver instance, AppPreferences prefs) {
    instance.prefs = prefs;
  }

  @InjectedFieldSignature("com.elion.assistant.service.notification.BootReceiver.notificationHelper")
  public static void injectNotificationHelper(BootReceiver instance,
      NotificationHelper notificationHelper) {
    instance.notificationHelper = notificationHelper;
  }
}
