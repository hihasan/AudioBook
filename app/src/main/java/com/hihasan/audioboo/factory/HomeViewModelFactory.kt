package com.hihasan.audioboo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hihasan.audioboo.views.home.HomeViewModel

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }

}