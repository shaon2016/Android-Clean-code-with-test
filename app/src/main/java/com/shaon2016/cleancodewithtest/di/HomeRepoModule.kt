package com.shaon2016.cleancodewithtest.di

import com.shaon2016.cleancodewithtest.data.local.RoomHelper
import com.shaon2016.cleancodewithtest.data.remote.ApiHelper
import com.shaon2016.cleancodewithtest.repo.home.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeRepoModule {

    @Singleton
    @Provides
    fun provideHomeLocalRepo(@AppModule.LocalRoomHelper roomHelper: RoomHelper): HomeLocalRepo {
        val dao = roomHelper.getDatabase().productDao()
        return HomeLocalRepoImpl(dao)
    }

    @Singleton
    @Provides
    fun provideHomeRemoteRepo(@AppModule.RemoteApiHelper apiHelper: ApiHelper): HomeRemoteRepo {
        return HomeRemoteRepoImpl(apiHelper)
    }

    @Singleton
    @Provides
    fun provideHomeRepo(
        homeLocalRepo: HomeLocalRepo,
        homeNetworkRepo: HomeRemoteRepo
    ): HomeRepo {
        return HomeRepoImpl(homeLocalRepo, homeNetworkRepo)
    }
}