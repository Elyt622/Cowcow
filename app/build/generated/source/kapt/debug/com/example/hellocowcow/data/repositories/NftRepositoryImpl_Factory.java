package com.example.hellocowcow.data.repositories;

import com.example.hellocowcow.data.network.api.MvxApi;
import com.example.hellocowcow.data.network.api.ProxyXoxnoApi;
import com.example.hellocowcow.data.network.api.XoxnoApi;
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
public final class NftRepositoryImpl_Factory implements Factory<NftRepositoryImpl> {
  private final Provider<MvxApi> mvxApiProvider;

  private final Provider<ProxyXoxnoApi> proxyXoxnoApiProvider;

  private final Provider<XoxnoApi> xoxnoApiProvider;

  public NftRepositoryImpl_Factory(Provider<MvxApi> mvxApiProvider,
      Provider<ProxyXoxnoApi> proxyXoxnoApiProvider, Provider<XoxnoApi> xoxnoApiProvider) {
    this.mvxApiProvider = mvxApiProvider;
    this.proxyXoxnoApiProvider = proxyXoxnoApiProvider;
    this.xoxnoApiProvider = xoxnoApiProvider;
  }

  @Override
  public NftRepositoryImpl get() {
    return newInstance(mvxApiProvider.get(), proxyXoxnoApiProvider.get(), xoxnoApiProvider.get());
  }

  public static NftRepositoryImpl_Factory create(Provider<MvxApi> mvxApiProvider,
      Provider<ProxyXoxnoApi> proxyXoxnoApiProvider, Provider<XoxnoApi> xoxnoApiProvider) {
    return new NftRepositoryImpl_Factory(mvxApiProvider, proxyXoxnoApiProvider, xoxnoApiProvider);
  }

  public static NftRepositoryImpl newInstance(MvxApi mvxApi, ProxyXoxnoApi proxyXoxnoApi,
      XoxnoApi xoxnoApi) {
    return new NftRepositoryImpl(mvxApi, proxyXoxnoApi, xoxnoApi);
  }
}
