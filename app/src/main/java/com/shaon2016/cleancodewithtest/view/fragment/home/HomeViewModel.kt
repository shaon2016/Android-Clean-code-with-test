package com.shaon2016.cleancodewithtest.view.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaon2016.cleancodewithtest.data.model.Product
import com.shaon2016.cleancodewithtest.data.model.ProductSearchResponse
import com.shaon2016.cleancodewithtest.repo.home.HomeRepoImpl
import com.shaon2016.cleancodewithtest.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.shaon2016.cleancodewithtest.data.Result
import com.shaon2016.cleancodewithtest.data.succeeded

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepoImpl: HomeRepoImpl) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        loadPreviousSearchedList()
    }

    private fun loadPreviousSearchedList() {
        // TODO load from local
    }


    fun doSearch(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                onLoading(true)

                val result = homeRepoImpl.getSearchedProducts(query)

                when (result) {
                    is Result.Success -> {
                        if (result.succeeded) {
                            _products.value = result.data.results
                        } else {
                            // TODO handle others
                        }
                    }
                    is Result.Error -> {
                        // TODO handle error
                    }
                }

                onLoading(false)
            }
        }
    }
}