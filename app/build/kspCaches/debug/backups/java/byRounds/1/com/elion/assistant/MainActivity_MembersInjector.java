package com.elion.assistant;

import com.elion.assistant.service.notification.NotificationHelper;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<NotificationHelper> notificationHelperProvider;

  public MainActivity_MembersInjector(Provider<NotificationHelper> notificationHelperProvider) {
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<NotificationHelper> notificationHelperProvider) {
    return new MainActivity_MembersInjector(notificationHelperProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectNotificationHelper(instance, notificationHelperProvider.get());
  }

  @InjectedFieldSignature("com.elion.assistant.MainActivity.notificationHelper")
  public static void injectNotificationHelper(MainActivity instance,
      NotificationHelper notificationHelper) {
    instance.notificationHelper = notificationHelper;
  }
}
