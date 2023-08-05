package com.example.hellocowcow.data.repositories;

import com.example.hellocowcow.data.network.api.MvxApi;
import com.example.hellocowcow.ui.viewmodels.util.MySchedulers;
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
  private final Provider<MySchedulers> mySchedulersProvider;

  private final Provider<MvxApi> mvxApiProvider;

  public TransactionRepositoryImpl_Factory(Provider<MySchedulers> mySchedulersProvider,
      Provider<MvxApi> mvxApiProvider) {
    this.mySchedulersProvider = mySchedulersProvider;
    this.mvxApiProvider = mvxApiProvider;
  }

  @Override
  public TransactionRepositoryImpl get() {
    return newInstance(mySchedulersProvider.get(), mvxApiProvider.get());
  }

  public static TransactionRepositoryImpl_Factory create(
      Provider<MySchedulers> mySchedulersProvider, Provider<MvxApi> mvxApiProvider) {
    return new TransactionRepositoryImpl_Factory(mySchedulersProvider, mvxApiProvider);
  }

  public static TransactionRepositoryImpl newInstance(MySchedulers mySchedulers, MvxApi mvxApi) {
    return new TransactionRepositoryImpl(mySchedulers, mvxApi);
  }
}
