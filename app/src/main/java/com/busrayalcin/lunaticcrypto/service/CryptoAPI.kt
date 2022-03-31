package com.busrayalcin.lunaticcrypto.service

import com.busrayalcin.lunaticcrypto.model.Crypto
import com.busrayalcin.lunaticcrypto.model.CryptoList
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    @GET("prices")
    suspend fun getPrices(
        @Query("key") key: String
    ): CryptoList

    @GET("currencies")
    suspend fun getCurrencies(
        @Query("key") key: String,
        @Query("ids") ids: String,
        @Query("attributes") attributes: String
    ): Crypto
}