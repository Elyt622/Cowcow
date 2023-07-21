package com.example.hellocowcow.app.di.repository

import com.example.hellocowcow.data.repositories.CCToolsRepositoryImpl
import com.example.hellocowcow.data.repositories.NftRepositoryImpl
import com.example.hellocowcow.data.repositories.TokenRepositoryImpl
import com.example.hellocowcow.data.repositories.TransactionRepositoryImpl
import com.example.hellocowcow.domain.repositories.CCToolsRepository
import com.example.hellocowcow.domain.repositories.NftRepository
import com.example.hellocowcow.domain.repositories.TokenRepository
import com.example.hellocowcow.domain.repositories.TransactionRepository
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

    @Binds
    abstract fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ) : TransactionRepository

    @Binds
    abstract fun bindCCToolsRepository(
        ccToolsRepositoryImpl: CCToolsRepositoryImpl
    ) : CCToolsRepository

    @Binds
    abstract fun bindNftRepository(
        nftRepositoryImpl: NftRepositoryImpl
    ) : NftRepository

}