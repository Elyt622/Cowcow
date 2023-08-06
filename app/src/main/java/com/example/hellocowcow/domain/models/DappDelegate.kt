package com.example.hellocowcow.domain.models

import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber

object DappDelegate: SignClient.DappDelegate {

    val pairing = CoreClient.Pairing.create()

    private val wcEventModelsSubject: BehaviorSubject<Sign.Model?> = BehaviorSubject.create()

    val wcEventModels: Observable<Sign.Model>
        get() = wcEventModelsSubject.hide()

    private var selectedSessionTopic: String = ""

    init {
        SignClient.setDappDelegate(this)

        val chains = listOf("mvx:1")
        val methods = listOf(
            "mvx_signTransaction",
            "mvx_signTransactions",
            "mvx_signMessage",
            "mvx_signLoginToken",
            "mvx_signNativeAuthToken"
        )
        val events = emptyList<String>()
        val namespaces =
            mapOf("mvx" to Sign.Model.Namespace.Proposal(chains, methods, events))

        val connectParams = Sign.Params.Connect(namespaces, null, null, pairing!!)
        SignClient.connect(connectParams,
            onSuccess = {
                Timber.tag("SignClient_Connect_Proposal_Send_Success")
                    .d("The sign client connection proposal was successfully sent to the wallet")
            },
            onError = { error ->
                Timber.tag("CONNECTION_ERROR").e(error.throwable.stackTraceToString())
            }
        )
    }

    override fun onSessionApproved(approvedSession: Sign.Model.ApprovedSession) {
        selectedSessionTopic = approvedSession.topic
        Observable
            .just(approvedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Approved").d("Session was approved by the wallet")
        Timber.tag("Session_Topic").d("Approved session's topic is: %s", approvedSession.topic)
    }

    override fun onSessionRejected(rejectedSession: Sign.Model.RejectedSession) {
        Observable
            .just(rejectedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Rejected").d("The wallet rejected the session")
    }

    override fun onSessionUpdate(updatedSession: Sign.Model.UpdatedSession) {
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

    override fun onSessionEvent(sessionEvent: Sign.Model.SessionEvent) {
        Observable
            .just(sessionEvent)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Received_Session_Event").d(sessionEvent.toString())
    }

    override fun onSessionDelete(deletedSession: Sign.Model.DeletedSession) {
        selectedSessionTopic = ""
        Observable
            .just(deletedSession)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Session_Deleted").d(deletedSession.toString())
    }

    override fun onSessionRequestResponse(response: Sign.Model.SessionRequestResponse) {
        Observable
            .just(response)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Received_Session_Request_Response").d(response.toString())
    }

    override fun onConnectionStateChange(state: Sign.Model.ConnectionState) {
        Observable
            .just(state)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Connection_State_Changed").d(state.toString())
    }

    override fun onError(error: Sign.Model.Error) {
        Observable
            .just(error)
            .subscribeOn(Schedulers.io())
            .subscribe{ session ->
                wcEventModelsSubject.onNext(session)
            }.isDisposed
        Timber.tag("Error_In_SignClient_SDK").e(error.toString())
    }
}