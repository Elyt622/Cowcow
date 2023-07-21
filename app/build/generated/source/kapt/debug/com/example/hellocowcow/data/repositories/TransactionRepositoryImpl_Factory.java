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
public final class TransactionRepositoryImpl_Factory implements Factory<TransactionRepositoryImpl> {
  private final Provider<MvxApi> mvxApiProvider;

  public TransactionRepositoryImpl_Factory(Provider<MvxApi> mvxApiProvider) {
    this.mvxApiProvider = mvxApiProvider;
  }

  @Override
  public TransactionRepositoryImpl get() {
    return newInstance(mvxApiProvider.get());
  }

  public static TransactionRepositoryImpl_Factory create(Provider<MvxApi> mvxApiProvider) {
    return new TransactionRepositoryImpl_Factory(mvxApiProvider);
  }

  public static TransactionRepositoryImpl newInstance(MvxApi mvxApi) {
    return new TransactionRepositoryImpl(mvxApi);
  }
}
