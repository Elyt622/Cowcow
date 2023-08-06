package com.example.hellocowcow.ui.viewmodels.activity

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.domain.models.DappDelegate
import com.walletconnect.sign.client.Sign
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val dappDelegate = DappDelegate
    val deeplinkPairingUri = dappDelegate.pairing?.uri

    var address: String = ""
    var topic: String= ""

    private val startActivitySubject: PublishSubject<Unit> = PublishSubject.create()


    fun onClick() : Observable<Unit> {
        dappDelegate.wcEventModels.subscribeBy(
            onNext = { session ->
            when (session) {
                is Sign.Model.ApprovedSession -> {
                    Timber.tag("Approve").d(topic)
                    topic = session.topic
                    address = session.accounts[0].removePrefix("mvx:1:")
                    startActivitySubject.onNext(Unit)
                }
                is Sign.Model.RejectedSession -> {
                    Timber.tag("Rejected").d("Rejected")
                }
                is Sign.Model.UpdatedSession -> {
                    Timber.tag("Updated").d("Updated")
                }
                is Sign.Model.Session -> {
                    Timber.tag("SessionExtended").d("SessionExtended")
                }
                is Sign.Model.SessionEvent -> {
                    Timber.tag("SessionEvent").d("SessionEvent")
                }
                is Sign.Model.DeletedSession -> {
                    Timber.tag("DeleteSession").d("DeleteSession")
                }
                is Sign.Model.SessionRequestResponse -> {
                    Timber.tag("SessionRequestResponse").d("SessionRequestResponse")
                }
                is Sign.Model.ConnectionState -> {
                    Timber.tag("ConnectionState").d("ConnectionState")
                }
                is Sign.Model.Error -> {
                    Timber.tag("Error").d("Error")
                }
                else -> {
                    Timber.tag("Else").d("Else")
                }
            }
        },
            onError = {
                Timber.tag("Subscribe_Error").d(it)
            }).isDisposed

        return startActivitySubject
    }

}