package com.example.hellocowcow.ui.viewmodels.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.hellocowcow.app.module.BaseViewModel
import com.example.hellocowcow.data.retrofit.mvxApi.request.Reward
import com.example.hellocowcow.data.retrofit.mvxApi.request.Transaction
import com.example.hellocowcow.domain.models.DomainAccount
import com.example.hellocowcow.domain.models.DomainTransaction
import com.example.hellocowcow.domain.repositories.NftRepository
import com.example.hellocowcow.domain.repositories.TransactionRepository
import com.example.hellocowcow.ui.viewmodels.util.MyWalletConnect
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.reown.sign.client.Sign
import com.reown.util.bytesToHex
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ipfs.multibase.binary.Base64
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.math.RoundingMode
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val nftRepository: NftRepository,
    private val transactionRepository: TransactionRepository,
    private val wc: MyWalletConnect
) : BaseViewModel() {

    private val _address = mutableStateOf("")
    val address: State<String> get() = _address

    fun setAddress(value: String) {
        _address.value = value
    }

    val gson: Gson = GsonBuilder().disableHtmlEscaping().create()
    private val openDialogSubject: PublishSubject<Unit> = PublishSubject.create()

    private lateinit var txRequest : Transaction

    private val _uiStateTx: MutableStateFlow<UiStateTx> = MutableStateFlow(UiStateTx.NoData)
    val uiStateTx: StateFlow<UiStateTx> = _uiStateTx

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiStateTx {
        data object NoData : UiStateTx()
        data class Send(val tx: DomainTransaction) : UiStateTx()
        data class Error(val error: String) : UiStateTx()
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val data: String) : UiState()
        data class Error(val error: String) : UiState()
    }

    init {
        onClick()
    }

    fun onClick() =
        wc.dAppDelegate
            .wcEventObservable
            .subscribeBy(
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
            }).addTo(disposable)


    private fun getRequest(
        account: DomainAccount
    ) = if (!account.isGuarded)
            Transaction(
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
        else {
            Transaction(
                nonce = account.nonce,
                value = "0",
                receiver = "erd1qqqqqqqqqqqqqpgqqgzzsl0re9e3u0t3mhv3jwg6zu63zssd7yqs3uu9jk",
                sender = account.address,
                gasPrice = 1000000000L,
                gasLimit = 30000000L,
                data = "Y2xhaW1SZXdhcmRz",
                chainID = "1",
                version = 2,
                options = 2,
                guardian = account.activeGuardianAddress,
            )
        }



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
        val guardianSignatureRegex = """guardianSignature=([a-fA-F0-9]+)""".toRegex()

        val signatureResult = signatureRegex.find(response)
        val guardianSignatureResult = guardianSignatureRegex.find(response)

        val signature = signatureResult?.groups?.get(1)?.value
        val guardianSignature = guardianSignatureResult?.groups?.get(1)?.value

        if (signature != null) {
            txRequest.signature = signature
            txRequest.guardianSignature = guardianSignature.toString()
            transactionRepository.sendTransaction(txRequest)
                .subscribeBy (
                    onNext = {  tx ->
                        _uiStateTx.value = UiStateTx.Send(tx)
                    },
                    onError = { err ->
                        _uiStateTx.value = UiStateTx.Error(err.message.toString())
                    }).addTo(disposable)

        } else {
            println("Signature not found")
        }
        return openDialogSubject
    }

    fun getUnclaimedMooveForUser() {
        getAllDataForUser()
            .map { base64Data -> extractData(base64Data) }
            .map { hex -> hex.toBigInteger(16).toBigDecimal(18) }
            .map { decimalValue -> decimalValue.setScale(4, RoundingMode.HALF_UP) }
            .subscribeBy(
                onNext = { data ->
                    _uiState.value = UiState.Success(data.toEngineeringString())
                },
                onError = { error ->
                    _uiState.value = UiState.Error(error.message.toString())
                }
            ).addTo(disposable)
    }

    private fun extractData(
        base64Data: String
    ) : String {
        var matchedValue = ""
        val regex =
            "B[0-9w-z+/][A-Za-z0-9+/]{8}A|C[A-P][A-Za-z0-9+/]{9}AA|C[Q-Za-f][A-Za-z0-9+/]{10}AA|C[g-v][A-Za-z0-9+/]{11}AA"
        val dataLength = base64Data.length
        val matches = Pattern
            .compile(regex)
            .matcher(base64Data.substring(dataLength-35, dataLength))
        if (matches.find()) {
            matchedValue = matches.group()
        }
        return Base64
            .decodeBase64(matchedValue)
            .bytesToHex()
            .substring(2)
    }

    private fun getAllDataForUser()
    : Observable<String> =
        nftRepository.getAllDataUsers(
            Reward(
                "erd1qqqqqqqqqqqqqpgqqgzzsl0re9e3u0t3mhv3jwg6zu63zssd7yqs3uu9jk",
                "getAllDataForUser",
                "0",
                arrayListOf(),
                address.value
            )
        ).map { it.returnData[0] }
}


