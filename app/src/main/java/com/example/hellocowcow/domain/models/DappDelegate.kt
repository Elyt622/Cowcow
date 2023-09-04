package com.example.hellocowcow.domain.models

import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import timber.log.Timber

object DappDelegate: SignClient.DappDelegate {

    var selectedSessionTopic: String? = null
        private set

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


    private val wcEventSubject: Subject<Sign.Model> = PublishSubject.create()
    val wcEventObservable: Observable<Sign.Model> = wcEventSubject.hide()


    init {
        SignClient.setDappDelegate(this)
    }

    override
    fun onSessionApproved(
        approvedSession: Sign.Model.ApprovedSession
    ) {
        selectedSessionTopic = approvedSession.topic
        wcEventSubject.onNext(approvedSession)

        Timber.tag("Session_Approved")
            .d("Approved session's topic is: %s", approvedSession.topic)
    }

    override
    fun onSessionRejected(
        rejectedSession: Sign.Model.RejectedSession
    ) {
        wcEventSubject.onNext(rejectedSession)
        Timber.tag("Session_Rejected")
            .d(rejectedSession.reason)
    }

    override
    fun onSessionUpdate(
        updatedSession: Sign.Model.UpdatedSession
    ) {
        wcEventSubject.onNext(updatedSession)
        Timber.tag("Session_Updated").d(updatedSession.toString())
    }

    override fun onSessionExtend(session: Sign.Model.Session) {
        wcEventSubject.onNext(session)
        Timber.tag("Session_Extended").d(session.toString())
    }

    override
    fun onSessionEvent(
        sessionEvent: Sign.Model.SessionEvent
    ) {
        wcEventSubject.onNext(sessionEvent)
        Timber.tag("Received_Session_Event").d(sessionEvent.toString())
    }

    override
    fun onSessionDelete(
        deletedSession: Sign.Model.DeletedSession
    ) {
        deselectAccountDetails()
        wcEventSubject.onNext(deletedSession)
        Timber.tag("Session_Deleted").d(deletedSession.toString())
    }

    override
    fun onSessionRequestResponse(
        response: Sign.Model.SessionRequestResponse
    ) {
        wcEventSubject.onNext(response)
        Timber.tag("Received_Session_Request_Response").d(response.toString())
    }

    override
    fun onConnectionStateChange(
        state: Sign.Model.ConnectionState
    ) {
        wcEventSubject.onNext(state)
        Timber.tag("Connection_State_Changed").d(state.toString())
    }

    override
    fun onError(
        error: Sign.Model.Error
    ) {
        wcEventSubject.onNext(error)
        Timber.tag("Error_In_SignClient_SDK").e(error.toString())
    }

    private fun deselectAccountDetails() {
        selectedSessionTopic = null
    }

}