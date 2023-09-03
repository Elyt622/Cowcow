package com.example.hellocowcow.domain.models

import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber

object DappDelegate: SignClient.DappDelegate {

    private val wcEventModelsSubject: BehaviorSubject<Sign.Model>
        get() = BehaviorSubject.create()

    val wcEventModels: Observable<Sign.Model>
        get() = wcEventModelsSubject.hide()

    init {
        SignClient.setDappDelegate(this)
    }

    override
    fun onSessionApproved(
        approvedSession: Sign.Model.ApprovedSession
    ) {
        Observable
            .just(approvedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Approved")
            .d("Approved session's topic is: %s", approvedSession.topic)
    }

    override
    fun onSessionRejected(
        rejectedSession: Sign.Model.RejectedSession
    ) {
        Observable
            .just(rejectedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Rejected")
            .d("The wallet rejected the session")
    }

    override
    fun onSessionUpdate(
        updatedSession: Sign.Model.UpdatedSession
    ) {
        Observable
            .just(updatedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Updated").d(updatedSession.toString())
    }

    override fun onSessionExtend(session: Sign.Model.Session) {
        Observable
            .just(session)
            .subscribeOn(Schedulers.io())
            .subscribe{
                wcEventModelsSubject.onNext(it)
            }.isDisposed
        Timber.tag("Session_Extended").d(session.toString())
    }

    override
    fun onSessionEvent(
        sessionEvent: Sign.Model.SessionEvent
    ) {
        Observable
            .just(sessionEvent)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Received_Session_Event").d(sessionEvent.toString())
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
        Timber.tag("Session_Deleted").d(deletedSession.toString())
    }

    override
    fun onSessionRequestResponse(
        response: Sign.Model.SessionRequestResponse
    ) {
        Observable
            .just(response)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Received_Session_Request_Response").d(response.toString())
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
        Timber.tag("Connection_State_Changed").d(state.toString())
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
        Timber.tag("Error_In_SignClient_SDK").e(error.toString())
    }
}