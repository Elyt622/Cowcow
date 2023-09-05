package com.example.hellocowcow.app.module

import androidx.activity.ComponentActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseActivity : ComponentActivity() {

    protected val disposable = CompositeDisposable()

    override
    fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}