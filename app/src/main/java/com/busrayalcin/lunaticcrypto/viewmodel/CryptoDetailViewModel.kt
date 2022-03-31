package com.busrayalcin.lunaticcrypto.viewmodel

import androidx.lifecycle.ViewModel
import com.busrayalcin.lunaticcrypto.model.Crypto
import com.busrayalcin.lunaticcrypto.repository.CryptoRepository
import com.busrayalcin.lunaticcrypto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : ViewModel(){

    suspend fun getCrypto(id : String) : Resource<Crypto>{
        return cryptoRepository.getCrypto(id)
    }

}
