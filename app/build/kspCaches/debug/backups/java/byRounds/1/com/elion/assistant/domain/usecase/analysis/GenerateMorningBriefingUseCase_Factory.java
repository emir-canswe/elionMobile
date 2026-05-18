package com.elion.assistant.domain.usecase.analysis;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class GenerateMorningBriefingUseCase_Factory implements Factory<GenerateMorningBriefingUseCase> {
  @Override
  public GenerateMorningBriefingUseCase get() {
    return newInstance();
  }

  public static GenerateMorningBriefingUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GenerateMorningBriefingUseCase newInstance() {
    return new GenerateMorningBriefingUseCase();
  }

  private static final class InstanceHolder {
    private static final GenerateMorningBriefingUseCase_Factory INSTANCE = new GenerateMorningBriefingUseCase_Factory();
  }
}
