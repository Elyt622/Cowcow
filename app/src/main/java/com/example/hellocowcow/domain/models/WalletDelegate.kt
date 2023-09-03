package com.example.hellocowcow.domain.models

import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber

object WalletDelegate: SignClient.WalletDelegate {

    private val wcEventModelsSubject: BehaviorSubject<Sign.Model>
        get() = BehaviorSubject.create()

    val wcEventModels: Observable<Sign.Model>
        get() = wcEventModelsSubject.hide()

    init {
        SignClient.setWalletDelegate(this)
    }

    override
    fun onConnectionStateChange(
        state: Sign.Model.ConnectionState
    ) {
        Observable
            .just(state)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_state")
            .d("State: %s", state)
    }

    override
    fun onError(
        error: Sign.Model.Error
    ) {
        Observable
            .just(error)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_error")
            .d("Error: %s", error)
    }

    override
    fun onSessionDelete(
        deletedSession: Sign.Model.DeletedSession
    ) {
        Observable
            .just(deletedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Deleted_Session")
            .d("DeletedSession: %s", deletedSession)
    }

    override
    fun onSessionProposal(
        sessionProposal: Sign.Model.SessionProposal,
        verifyContext: Sign.Model.VerifyContext
    ) {
        Observable
            .just(sessionProposal)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Proposal")
            .d("Proposer Public Key: %s", sessionProposal.proposerPublicKey)
    }

    override
    fun onSessionRequest(
        sessionRequest: Sign.Model.SessionRequest,
        verifyContext: Sign.Model.VerifyContext
    ) {
        Observable
            .just(sessionRequest)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Request")
            .d("Request: %s", sessionRequest.request)
    }

    override
    fun onSessionSettleResponse(
        settleSessionResponse: Sign.Model.SettledSessionResponse
    ) {
        Observable
            .just(settleSessionResponse)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Settle_Session")
            .d("SettleSession: %s", settleSessionResponse)
    }

    override
    fun onSessionUpdateResponse(
        sessionUpdateResponse: Sign.Model.SessionUpdateResponse
    ) {
        Observable
            .just(sessionUpdateResponse)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Update")
            .d("Session Update: %s", sessionUpdateResponse)
    }
}