package com.elion.assistant;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.elion.assistant.data.local.database.AppDatabase;
import com.elion.assistant.data.local.database.dao.CategoryDao;
import com.elion.assistant.data.local.database.dao.DailyStatDao;
import com.elion.assistant.data.local.database.dao.TaskDao;
import com.elion.assistant.data.local.preferences.AppPreferences;
import com.elion.assistant.data.repository.CategoryRepositoryImpl;
import com.elion.assistant.data.repository.StatRepositoryImpl;
import com.elion.assistant.data.repository.TaskRepositoryImpl;
import com.elion.assistant.di.DatabaseModule_ProvideCategoryDaoFactory;
import com.elion.assistant.di.DatabaseModule_ProvideDailyStatDaoFactory;
import com.elion.assistant.di.DatabaseModule_ProvideDatabaseFactory;
import com.elion.assistant.di.DatabaseModule_ProvideTaskDaoFactory;
import com.elion.assistant.domain.usecase.analysis.CheckPostponedTasksUseCase;
import com.elion.assistant.domain.usecase.analysis.GenerateEveningAnalysisUseCase;
import com.elion.assistant.domain.usecase.analysis.GenerateMorningBriefingUseCase;
import com.elion.assistant.domain.usecase.voice.ExecuteVoiceCommandUseCase;
import com.elion.assistant.domain.usecase.voice.ParseVoiceCommandUseCase;
import com.elion.assistant.presentation.home.HomeViewModel;
import com.elion.assistant.presentation.home.HomeViewModel_HiltModules;
import com.elion.assistant.presentation.settings.SettingsViewModel;
import com.elion.assistant.presentation.settings.SettingsViewModel_HiltModules;
import com.elion.assistant.presentation.stats.StatsViewModel;
import com.elion.assistant.presentation.stats.StatsViewModel_HiltModules;
import com.elion.assistant.presentation.tasks.TasksViewModel;
import com.elion.assistant.presentation.tasks.TasksViewModel_HiltModules;
import com.elion.assistant.presentation.voice.VoiceViewModel;
import com.elion.assistant.presentation.voice.VoiceViewModel_HiltModules;
import com.elion.assistant.service.notification.BootReceiver;
import com.elion.assistant.service.notification.BootReceiver_MembersInjector;
import com.elion.assistant.service.notification.EveningAnalysisWorker;
import com.elion.assistant.service.notification.EveningAnalysisWorker_AssistedFactory;
import com.elion.assistant.service.notification.MorningBriefingWorker;
import com.elion.assistant.service.notification.MorningBriefingWorker_AssistedFactory;
import com.elion.assistant.service.notification.NotificationHelper;
import com.elion.assistant.service.voice.SpeechRecognitionManager;
import com.elion.assistant.service.voice.TextToSpeechManager;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerElionApplication_HiltComponents_SingletonC {
  private DaggerElionApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ElionApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ElionApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ElionApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ElionApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ElionApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ElionApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ElionApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ElionApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ElionApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ElionApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ElionApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ElionApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ElionApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.com_elion_assistant_presentation_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_elion_assistant_presentation_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_elion_assistant_presentation_stats_StatsViewModel, StatsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_elion_assistant_presentation_tasks_TasksViewModel, TasksViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_elion_assistant_presentation_voice_VoiceViewModel, VoiceViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectNotificationHelper(instance, singletonCImpl.notificationHelperProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_elion_assistant_presentation_home_HomeViewModel = "com.elion.assistant.presentation.home.HomeViewModel";

      static String com_elion_assistant_presentation_settings_SettingsViewModel = "com.elion.assistant.presentation.settings.SettingsViewModel";

      static String com_elion_assistant_presentation_tasks_TasksViewModel = "com.elion.assistant.presentation.tasks.TasksViewModel";

      static String com_elion_assistant_presentation_voice_VoiceViewModel = "com.elion.assistant.presentation.voice.VoiceViewModel";

      static String com_elion_assistant_presentation_stats_StatsViewModel = "com.elion.assistant.presentation.stats.StatsViewModel";

      @KeepFieldType
      HomeViewModel com_elion_assistant_presentation_home_HomeViewModel2;

      @KeepFieldType
      SettingsViewModel com_elion_assistant_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      TasksViewModel com_elion_assistant_presentation_tasks_TasksViewModel2;

      @KeepFieldType
      VoiceViewModel com_elion_assistant_presentation_voice_VoiceViewModel2;

      @KeepFieldType
      StatsViewModel com_elion_assistant_presentation_stats_StatsViewModel2;
    }
  }

  private static final class ViewModelCImpl extends ElionApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<StatsViewModel> statsViewModelProvider;

    private Provider<TasksViewModel> tasksViewModelProvider;

    private Provider<VoiceViewModel> voiceViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private ExecuteVoiceCommandUseCase executeVoiceCommandUseCase() {
      return new ExecuteVoiceCommandUseCase(singletonCImpl.taskRepositoryImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.statsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.tasksViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.voiceViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.com_elion_assistant_presentation_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_elion_assistant_presentation_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)).put(LazyClassKeyProvider.com_elion_assistant_presentation_stats_StatsViewModel, ((Provider) statsViewModelProvider)).put(LazyClassKeyProvider.com_elion_assistant_presentation_tasks_TasksViewModel, ((Provider) tasksViewModelProvider)).put(LazyClassKeyProvider.com_elion_assistant_presentation_voice_VoiceViewModel, ((Provider) voiceViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_elion_assistant_presentation_stats_StatsViewModel = "com.elion.assistant.presentation.stats.StatsViewModel";

      static String com_elion_assistant_presentation_settings_SettingsViewModel = "com.elion.assistant.presentation.settings.SettingsViewModel";

      static String com_elion_assistant_presentation_tasks_TasksViewModel = "com.elion.assistant.presentation.tasks.TasksViewModel";

      static String com_elion_assistant_presentation_home_HomeViewModel = "com.elion.assistant.presentation.home.HomeViewModel";

      static String com_elion_assistant_presentation_voice_VoiceViewModel = "com.elion.assistant.presentation.voice.VoiceViewModel";

      @KeepFieldType
      StatsViewModel com_elion_assistant_presentation_stats_StatsViewModel2;

      @KeepFieldType
      SettingsViewModel com_elion_assistant_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      TasksViewModel com_elion_assistant_presentation_tasks_TasksViewModel2;

      @KeepFieldType
      HomeViewModel com_elion_assistant_presentation_home_HomeViewModel2;

      @KeepFieldType
      VoiceViewModel com_elion_assistant_presentation_voice_VoiceViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.elion.assistant.presentation.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.taskRepositoryImplProvider.get(), singletonCImpl.statRepositoryImplProvider.get(), new CheckPostponedTasksUseCase(), new GenerateMorningBriefingUseCase(), singletonCImpl.appPreferencesProvider.get());

          case 1: // com.elion.assistant.presentation.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.appPreferencesProvider.get(), singletonCImpl.provideDatabaseProvider.get());

          case 2: // com.elion.assistant.presentation.stats.StatsViewModel 
          return (T) new StatsViewModel(singletonCImpl.statRepositoryImplProvider.get());

          case 3: // com.elion.assistant.presentation.tasks.TasksViewModel 
          return (T) new TasksViewModel(singletonCImpl.taskRepositoryImplProvider.get(), singletonCImpl.categoryRepositoryImplProvider.get());

          case 4: // com.elion.assistant.presentation.voice.VoiceViewModel 
          return (T) new VoiceViewModel(singletonCImpl.speechRecognitionManagerProvider.get(), singletonCImpl.textToSpeechManagerProvider.get(), new ParseVoiceCommandUseCase(), viewModelCImpl.executeVoiceCommandUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ElionApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ElionApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ElionApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<TaskRepositoryImpl> taskRepositoryImplProvider;

    private Provider<StatRepositoryImpl> statRepositoryImplProvider;

    private Provider<NotificationHelper> notificationHelperProvider;

    private Provider<AppPreferences> appPreferencesProvider;

    private Provider<TextToSpeechManager> textToSpeechManagerProvider;

    private Provider<EveningAnalysisWorker_AssistedFactory> eveningAnalysisWorker_AssistedFactoryProvider;

    private Provider<MorningBriefingWorker_AssistedFactory> morningBriefingWorker_AssistedFactoryProvider;

    private Provider<CategoryRepositoryImpl> categoryRepositoryImplProvider;

    private Provider<SpeechRecognitionManager> speechRecognitionManagerProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private TaskDao taskDao() {
      return DatabaseModule_ProvideTaskDaoFactory.provideTaskDao(provideDatabaseProvider.get());
    }

    private DailyStatDao dailyStatDao() {
      return DatabaseModule_ProvideDailyStatDaoFactory.provideDailyStatDao(provideDatabaseProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return MapBuilder.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>newMapBuilder(2).put("com.elion.assistant.service.notification.EveningAnalysisWorker", ((Provider) eveningAnalysisWorker_AssistedFactoryProvider)).put("com.elion.assistant.service.notification.MorningBriefingWorker", ((Provider) morningBriefingWorker_AssistedFactoryProvider)).build();
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private CategoryDao categoryDao() {
      return DatabaseModule_ProvideCategoryDaoFactory.provideCategoryDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.taskRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<TaskRepositoryImpl>(singletonCImpl, 1));
      this.statRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<StatRepositoryImpl>(singletonCImpl, 3));
      this.notificationHelperProvider = DoubleCheck.provider(new SwitchingProvider<NotificationHelper>(singletonCImpl, 4));
      this.appPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<AppPreferences>(singletonCImpl, 5));
      this.textToSpeechManagerProvider = DoubleCheck.provider(new SwitchingProvider<TextToSpeechManager>(singletonCImpl, 6));
      this.eveningAnalysisWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<EveningAnalysisWorker_AssistedFactory>(singletonCImpl, 0));
      this.morningBriefingWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<MorningBriefingWorker_AssistedFactory>(singletonCImpl, 7));
      this.categoryRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<CategoryRepositoryImpl>(singletonCImpl, 8));
      this.speechRecognitionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SpeechRecognitionManager>(singletonCImpl, 9));
    }

    @Override
    public void injectElionApplication(ElionApplication elionApplication) {
      injectElionApplication2(elionApplication);
    }

    @Override
    public void injectBootReceiver(BootReceiver bootReceiver) {
      injectBootReceiver2(bootReceiver);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private ElionApplication injectElionApplication2(ElionApplication instance) {
      ElionApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private BootReceiver injectBootReceiver2(BootReceiver instance2) {
      BootReceiver_MembersInjector.injectPrefs(instance2, appPreferencesProvider.get());
      BootReceiver_MembersInjector.injectNotificationHelper(instance2, notificationHelperProvider.get());
      return instance2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.elion.assistant.service.notification.EveningAnalysisWorker_AssistedFactory 
          return (T) new EveningAnalysisWorker_AssistedFactory() {
            @Override
            public EveningAnalysisWorker create(Context context, WorkerParameters params) {
              return new EveningAnalysisWorker(context, params, singletonCImpl.taskRepositoryImplProvider.get(), singletonCImpl.statRepositoryImplProvider.get(), new GenerateEveningAnalysisUseCase(), singletonCImpl.notificationHelperProvider.get(), singletonCImpl.appPreferencesProvider.get(), singletonCImpl.textToSpeechManagerProvider.get());
            }
          };

          case 1: // com.elion.assistant.data.repository.TaskRepositoryImpl 
          return (T) new TaskRepositoryImpl(singletonCImpl.taskDao());

          case 2: // com.elion.assistant.data.local.database.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.elion.assistant.data.repository.StatRepositoryImpl 
          return (T) new StatRepositoryImpl(singletonCImpl.dailyStatDao());

          case 4: // com.elion.assistant.service.notification.NotificationHelper 
          return (T) new NotificationHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.elion.assistant.data.local.preferences.AppPreferences 
          return (T) new AppPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.elion.assistant.service.voice.TextToSpeechManager 
          return (T) new TextToSpeechManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.elion.assistant.service.notification.MorningBriefingWorker_AssistedFactory 
          return (T) new MorningBriefingWorker_AssistedFactory() {
            @Override
            public MorningBriefingWorker create(Context context2, WorkerParameters params2) {
              return new MorningBriefingWorker(context2, params2, singletonCImpl.taskRepositoryImplProvider.get(), new GenerateMorningBriefingUseCase(), singletonCImpl.notificationHelperProvider.get(), singletonCImpl.appPreferencesProvider.get(), singletonCImpl.textToSpeechManagerProvider.get());
            }
          };

          case 8: // com.elion.assistant.data.repository.CategoryRepositoryImpl 
          return (T) new CategoryRepositoryImpl(singletonCImpl.categoryDao());

          case 9: // com.elion.assistant.service.voice.SpeechRecognitionManager 
          return (T) new SpeechRecognitionManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
