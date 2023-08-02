package com.example.hellocowcow.app.di.viewModels

import com.example.hellocowcow.ui.viewmodels.util.MySchedulers
import com.example.hellocowcow.ui.viewmodels.util.MySchedulersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsPresenterModule {

    @Binds
    abstract fun bindNetworkSchedulers(
        networkSchedulers: MySchedulersImpl
    ): MySchedulers

}