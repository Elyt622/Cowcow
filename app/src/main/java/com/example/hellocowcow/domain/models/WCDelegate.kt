package com.example.hellocowcow.domain.models

import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.web3.wallet.client.Wallet
import com.walletconnect.web3.wallet.client.Web3Wallet
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

object WCDelegate: Web3Wallet.WalletDelegate, CoreClient.CoreDelegate {

    private val chains = listOf("mvx:1")
    private val methods = listOf(
        "mvx_signTransaction",
        "mvx_signTransactions",
        "mvx_signMessage",
        "mvx_signLoginToken",
        "mvx_signNativeAuthToken"
    )
    private val events = emptyList<String>()
    val namespaces =
        mapOf("mvx" to Sign.Model.Namespace.Proposal(chains, methods, events))

    var authRequestEvent: Pair<Wallet.Model.AuthRequest, Wallet.Model.VerifyContext>? = null
    var sessionProposalEvent: Pair<Wallet.Model.SessionProposal, Wallet.Model.VerifyContext>? = null
    var sessionRequestEvent: Pair<Wallet.Model.SessionRequest, Wallet.Model.VerifyContext>? = null

    private val wcEventSubject: Subject<Wallet.Model> = PublishSubject.create()
    val wcEventObservable: Observable<Wallet.Model> = wcEventSubject.hide()

    private val coreEventSubject: Subject<Core.Model> = PublishSubject.create()
    val coreEventObservable: Observable<Core.Model> = coreEventSubject.hide()

    init {
        CoreClient.setDelegate(this)
        Web3Wallet.setWalletDelegate(this)
    }

    override
    fun onAuthRequest(
        authRequest: Wallet.Model.AuthRequest,
        verifyContext: Wallet.Model.VerifyContext
    ) {
        authRequestEvent = Pair(authRequest, verifyContext)
        wcEventSubject.onNext(authRequest)
    }

    override
    fun onConnectionStateChange(
        state: Wallet.Model.ConnectionState
    ) {
        wcEventSubject.onNext(state)
    }

    override
    fun onError(error: Wallet.Model.Error) {
        wcEventSubject.onNext(error)
    }

    override
    fun onSessionDelete(sessionDelete: Wallet.Model.SessionDelete) {
        wcEventSubject.onNext(sessionDelete)
    }

    override
    fun onSessionProposal(
        sessionProposal: Wallet.Model.SessionProposal,
        verifyContext: Wallet.Model.VerifyContext
    ) {
        sessionProposalEvent = Pair(sessionProposal, verifyContext)
        wcEventSubject.onNext(sessionProposal)
    }

    override
    fun onSessionRequest(
        sessionRequest: Wallet.Model.SessionRequest,
        verifyContext: Wallet.Model.VerifyContext
    ) {
        sessionRequestEvent = Pair(sessionRequest, verifyContext)
        wcEventSubject.onNext(sessionRequest)
    }

    override
    fun onSessionSettleResponse(
        settleSessionResponse: Wallet.Model.SettledSessionResponse
    ) {
        wcEventSubject.onNext(settleSessionResponse)
    }

    override
    fun onSessionUpdateResponse(
        sessionUpdateResponse: Wallet.Model.SessionUpdateResponse
    ) {
        wcEventSubject.onNext(sessionUpdateResponse)
    }

    override
    fun onPairingDelete(
        deletedPairing: Core.Model.DeletedPairing
    ) {
        coreEventSubject.onNext(deletedPairing)
    }


}