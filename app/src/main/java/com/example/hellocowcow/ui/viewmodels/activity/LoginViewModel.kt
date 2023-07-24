package com.example.hellocowcow.ui.viewmodels.activity

import androidx.lifecycle.ViewModel
import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber

class LoginViewModel : ViewModel() {

    var apprSession: Sign.Model.ApprovedSession? = null
    val pairing = CoreClient.Pairing.create()
    val deeplinkPairingUri = pairing!!.uri
    lateinit var address: String
    lateinit var topic: String

    private val startActivitySubject: PublishSubject<Unit> = PublishSubject.create()

    fun onClick() : Observable<Unit> {

        val dappDelegate = object : SignClient.DappDelegate {
            override fun onSessionApproved(approvedSession: Sign.Model.ApprovedSession) {
                apprSession = approvedSession
                // Triggered when dapp receives the session approval from wallet
                Timber.tag("Session_Approved").d("Session was approved by the wallet")
                Timber.tag("Session_Topic").d("Approved session's topic is: %s", approvedSession.topic)
                Timber.tag("ACCOUNT").d(apprSession!!.accounts[0])
                topic = apprSession!!.topic
                address = apprSession!!.accounts[0].removePrefix("mvx:D:")
                startActivitySubject.onNext(Unit)
            }

            override fun onSessionRejected(rejectedSession: Sign.Model.RejectedSession) {
                // Triggered when Dapp receives the session rejection from wallet
                Timber.tag("Session_Rejected").d("The wallet rejected the session")
            }

            override fun onSessionUpdate(updatedSession: Sign.Model.UpdatedSession) {
                // Triggered when Dapp receives the session update from wallet
                Timber.tag("Session_Updated").d(updatedSession.toString())
            }

            override fun onSessionExtend(session: Sign.Model.Session) {
                // Triggered when Dapp receives the session extend from wallet
                Timber.tag("Session_Extended").d(session.toString())
            }

            override fun onSessionEvent(sessionEvent: Sign.Model.SessionEvent) {
                // Triggered when the peer emits events that match the list of events agreed upon session settlement
                Timber.tag("Received_Session_Event").d(sessionEvent.toString())
            }

            override fun onSessionDelete(deletedSession: Sign.Model.DeletedSession) {
                Timber.tag("Session_Deleted").d(deletedSession.toString())
            }

            override fun onSessionRequestResponse(response: Sign.Model.SessionRequestResponse) {
                Timber.tag("Received_Session_Request_Response").d(response.toString())
            }

            override fun onConnectionStateChange(state: Sign.Model.ConnectionState) {
                //Triggered whenever the connection state is changed
                Timber.tag("Connection_State_Changed").d(state.toString())
            }

            override fun onError(error: Sign.Model.Error) {
                // Triggered whenever there is an issue inside the SDK
                Timber.tag("Error_In_SignClient_SDK").e(error.toString())
            }
        }

        SignClient.setDappDelegate(dappDelegate)
        val chains = listOf("mvx:D")
        val methods = listOf(
            "mvx_signTransaction",
            "mvx_signTransactions",
            "mvx_signMessage",
            "mvx_signLoginToken"
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
        return startActivitySubject
    }

}