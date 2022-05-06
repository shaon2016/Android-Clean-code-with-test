package com.shaon2016.cleancodewithtest.data.network

import com.shaon2016.cleancodewithtest.di.AppModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class MockNetworkModule : AppModule() {
    override var baseUrl = "https://localhost:8000"
}