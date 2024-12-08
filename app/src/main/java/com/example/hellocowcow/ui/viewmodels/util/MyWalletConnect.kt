package com.example.hellocowcow.ui.viewmodels.util

import com.example.hellocowcow.domain.models.DAppDelegate
import javax.inject.Inject

interface DAppDelegateProvider {
  val dAppDelegate: DAppDelegate
    get() = DAppDelegate
}

interface MyWalletConnect : DAppDelegateProvider
class MyWalletConnectImpl @Inject constructor() : MyWalletConnect