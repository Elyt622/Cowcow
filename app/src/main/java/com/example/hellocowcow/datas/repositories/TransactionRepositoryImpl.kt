package com.example.hellocowcow.datas.repositories

import com.example.hellocowcow.datas.network.api.MvxApi
import com.example.hellocowcow.domain.repositories.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val mvxApi: MvxApi
) : TransactionRepository {

}