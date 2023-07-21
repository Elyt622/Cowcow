package com.example.hellocowcow.data.repositories;

import com.example.hellocowcow.data.network.api.CCToolsApi;
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
    "KotlinInternalInJava"
})
public final class CCToolsRepositoryImpl_Factory implements Factory<CCToolsRepositoryImpl> {
  private final Provider<CCToolsApi> ccToolsApiProvider;

  public CCToolsRepositoryImpl_Factory(Provider<CCToolsApi> ccToolsApiProvider) {
    this.ccToolsApiProvider = ccToolsApiProvider;
  }

  @Override
  public CCToolsRepositoryImpl get() {
    return newInstance(ccToolsApiProvider.get());
  }

  public static CCToolsRepositoryImpl_Factory create(Provider<CCToolsApi> ccToolsApiProvider) {
    return new CCToolsRepositoryImpl_Factory(ccToolsApiProvider);
  }

  public static CCToolsRepositoryImpl newInstance(CCToolsApi ccToolsApi) {
    return new CCToolsRepositoryImpl(ccToolsApi);
  }
}
