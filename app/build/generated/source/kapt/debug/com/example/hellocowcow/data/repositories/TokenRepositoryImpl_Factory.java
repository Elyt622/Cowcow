package com.example.hellocowcow.data.repositories;

import com.example.hellocowcow.data.network.api.MvxApi;
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
public final class TokenRepositoryImpl_Factory implements Factory<TokenRepositoryImpl> {
  private final Provider<MvxApi> mvxApiProvider;

  public TokenRepositoryImpl_Factory(Provider<MvxApi> mvxApiProvider) {
    this.mvxApiProvider = mvxApiProvider;
  }

  @Override
  public TokenRepositoryImpl get() {
    return newInstance(mvxApiProvider.get());
  }

  public static TokenRepositoryImpl_Factory create(Provider<MvxApi> mvxApiProvider) {
    return new TokenRepositoryImpl_Factory(mvxApiProvider);
  }

  public static TokenRepositoryImpl newInstance(MvxApi mvxApi) {
    return new TokenRepositoryImpl(mvxApi);
  }
}
