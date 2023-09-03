package com.example.hellocowcow.app.di.walletconnect

import com.example.hellocowcow.ui.viewmodels.util.MyWalletConnect
import com.example.hellocowcow.ui.viewmodels.util.MyWalletConnectImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsWalletConnectModule {

    @Binds
    abstract fun bindWalletConnect(
        walletConnectImpl: MyWalletConnectImpl
    ): MyWalletConnect
}