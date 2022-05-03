package com.android.rickandmorty.di

import com.android.rickandmorty.data.network.RMService
import com.android.rickandmorty.data.repository.CharacterRepository
import com.android.rickandmorty.data.repository.CharacterRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(service: RMService): CharacterRepository =
        CharacterRepositoryImp(service)

}