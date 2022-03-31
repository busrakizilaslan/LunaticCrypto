package com.busrayalcin.lunaticcrypto.repository

import com.busrayalcin.lunaticcrypto.model.Crypto
import com.busrayalcin.lunaticcrypto.model.CryptoList
import com.busrayalcin.lunaticcrypto.service.CryptoAPI
import com.busrayalcin.lunaticcrypto.util.Constants.API_KEY
import com.busrayalcin.lunaticcrypto.util.Constants.CALL_ATTRIBUTES
import com.busrayalcin.lunaticcrypto.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
){
    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
            api.getPrices(API_KEY)
        }catch (e: Exception){
            return Resource.Error(e.message ?: "Error Occurred")
        }
        return Resource.Success(response)
    }
    suspend fun getCrypto(id : String) : Resource<Crypto> {
        val response = try {
            api.getCurrencies(API_KEY, id,CALL_ATTRIBUTES)
        }catch (e: Exception){
            return Resource.Error(e.message ?: "Error Occurred")
        }
        return Resource.Success(response)
    }
}