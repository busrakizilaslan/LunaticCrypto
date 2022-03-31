package com.busrayalcin.lunaticcrypto.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.busrayalcin.lunaticcrypto.model.CryptoListItem
import com.busrayalcin.lunaticcrypto.repository.CryptoRepository
import com.busrayalcin.lunaticcrypto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStarting = true


    init {
        loadCryptos()
    }

    fun searchCryptoList(query: String) {
        val listToSearch = if (isSearchStarting) {
            cryptoList.value
        } else {
            initialCryptoList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isNotEmpty()) {
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.currency.contains(query.trim(), true)
            }
            if (isSearchStarting) {
                initialCryptoList = cryptoList.value
                isSearchStarting = false
            }
            cryptoList.value = results
        }
    }

    fun loadCryptos() {

        viewModelScope.launch {
            isLoading.value = true
            val result = cryptoRepository.getCryptoList()
            when (result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency, cryptoListItem.price)
                    }
                    cryptoList.value += cryptoItems
                    isLoading.value = false
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}

