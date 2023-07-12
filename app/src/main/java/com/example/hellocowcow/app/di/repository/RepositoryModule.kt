package com.example.hellocowcow.app.di.repository

import com.example.hellocowcow.datas.repositories.TokenRepositoryImpl
import com.example.hellocowcow.domain.repositories.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ) : TokenRepository

}