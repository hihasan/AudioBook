package com.hihasan.audioboo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltModules {
//    @Provides
//    fun provideProductRepository(): ProductRepository {
//        var productApi = RetrofitHelper.createService(ProductApi::class.java, APIClient.BASE_URL, true)
//        return ProductRepository(productApi)
//    }
}