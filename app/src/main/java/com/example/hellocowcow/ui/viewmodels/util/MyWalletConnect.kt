package com.example.hellocowcow.ui.viewmodels.util

import com.example.hellocowcow.domain.models.DappDelegate
import com.example.hellocowcow.domain.models.WalletDelegate
import javax.inject.Inject

interface DAppDelegateProvider {
    val dAppDelegate: DappDelegate
        get() = DappDelegate
}

interface WalletDelegateProvider {
    val walletDelegate: WalletDelegate
        get() = WalletDelegate
}

interface MyWalletConnect : DAppDelegateProvider, WalletDelegateProvider
class MyWalletConnectImpl @Inject constructor() : MyWalletConnect