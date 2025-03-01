package com.example.hellocowcow.app.module

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

  protected val disposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    disposable.clear()
  }
}