package com.example.hellocowcow.ui.viewmodels.util

import com.example.hellocowcow.domain.models.DappDelegate
import javax.inject.Inject

interface DAppDelegateProvider {
    val dAppDelegate: DappDelegate
        get() = DappDelegate
}

interface MyWalletConnect : DAppDelegateProvider
class MyWalletConnectImpl @Inject constructor() : MyWalletConnect