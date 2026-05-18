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
public final class GenerateEveningAnalysisUseCase_Factory implements Factory<GenerateEveningAnalysisUseCase> {
  @Override
  public GenerateEveningAnalysisUseCase get() {
    return newInstance();
  }

  public static GenerateEveningAnalysisUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GenerateEveningAnalysisUseCase newInstance() {
    return new GenerateEveningAnalysisUseCase();
  }

  private static final class InstanceHolder {
    private static final GenerateEveningAnalysisUseCase_Factory INSTANCE = new GenerateEveningAnalysisUseCase_Factory();
  }
}
