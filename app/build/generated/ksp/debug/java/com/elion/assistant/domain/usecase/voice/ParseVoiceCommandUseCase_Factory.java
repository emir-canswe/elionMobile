package com.elion.assistant.domain.usecase.voice;

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
public final class ParseVoiceCommandUseCase_Factory implements Factory<ParseVoiceCommandUseCase> {
  @Override
  public ParseVoiceCommandUseCase get() {
    return newInstance();
  }

  public static ParseVoiceCommandUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ParseVoiceCommandUseCase newInstance() {
    return new ParseVoiceCommandUseCase();
  }

  private static final class InstanceHolder {
    private static final ParseVoiceCommandUseCase_Factory INSTANCE = new ParseVoiceCommandUseCase_Factory();
  }
}
