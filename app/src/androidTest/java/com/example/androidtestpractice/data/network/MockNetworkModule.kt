package com.example.androidtestpractice.data.network

import com.example.androidtestpractice.di.AppModule
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