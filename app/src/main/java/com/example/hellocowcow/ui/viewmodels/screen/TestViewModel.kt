package com.example.hellocowcow.ui.viewmodels.screen

import androidx.lifecycle.ViewModel
import com.example.hellocowcow.data.retrofit.mvxApi.request.Transaction
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.models.DomainTransaction
import com.example.hellocowcow.domain.repositories.TransactionRepository
import com.example.hellocowcow.ui.viewmodels.util.MyDAppDelegate
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.walletconnect.sign.client.Sign
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val dAppDelegate: MyDAppDelegate
) : ViewModel() {

    val gson: Gson = GsonBuilder().disableHtmlEscaping().create()
    private val openDialogSubject: PublishSubject<Unit> = PublishSubject.create()

    private lateinit var txRequest : Transaction

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.NoData)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        object NoData : UiState()
        data class Send(val tx: DomainTransaction) : UiState()
        data class Error(val error: String) : UiState()
    }

    init {
        onClick()
    }

    fun onClick() {
        dAppDelegate.dAppDelegate.wcEventModels.subscribeBy(
            onNext = { session ->
                when (session) {
                    is Sign.Model.SessionEvent -> {
                        Timber.tag("SessionEvent")
                            .d("SessionEvent")
                    }

                    is Sign.Model.SessionRequestResponse -> {
                        getSignatureAndSendRequest(
                            session.result.toString(),
                            txRequest
                        )
                        Timber.tag("SessionRequestResponse")
                            .d("SessionRequestResponse")
                    }

                    is Sign.Model.Error -> {
                        Timber.tag("Error")
                            .d(session.throwable)
                    }

                    else -> {
                        Timber.tag("Else")
                            .d("Else")
                    }
                }
            },
            onError = {
                Timber.tag("Subscribe_Error")
                    .d(it)
            }).isDisposed
    }

    private fun getRequest(
        account: DomainAccount
    ) = Transaction(
            nonce = account.nonce,
            value = "0",
            receiver = "erd1qqqqqqqqqqqqqpgqqgzzsl0re9e3u0t3mhv3jwg6zu63zssd7yqs3uu9jk",
            sender = account.address,
            gasPrice = 1000000000L,
            gasLimit = 30000000L,
            data = "Y2xhaW1SZXdhcmRz",
            chainID = "1",
            version = 1
        )


    fun buildClaimRewardRequest(
        account: DomainAccount,
        topic: String
    ): Sign.Params.Request {

        txRequest = getRequest(account)
        val jsonRequest = gson.toJson(txRequest)

        return Sign.Params.Request(
            sessionTopic = topic,
            method = "mvx_signTransaction",
            chainId = "mvx:1",
            params = """{"transaction":$jsonRequest}"""
        )
    }

    private fun getSignatureAndSendRequest(
        response: String,
        txRequest: Transaction
    ) : Observable<Unit> {

        val signatureRegex = """signature=([a-fA-F0-9]+)""".toRegex()
        val matchResult = signatureRegex.find(response)

        val signature = matchResult?.groups?.get(1)?.value

        if (signature != null) {
            println("Signature: $signature")
            txRequest.signature = signature
            Timber.tag("TRANSACTION_AFTER_BUILD").d(txRequest.nonce.toString())
            transactionRepository.sendTransaction(txRequest)
                .subscribeBy (
                    onNext = {  tx ->
                        _uiState.value = UiState.Send(tx)
                    },
                    onError = { err ->
                        _uiState.value = UiState.Error(err.message.toString())
                    }).isDisposed

        } else {
            println("Signature not found")
        }
        return openDialogSubject
    }

}
