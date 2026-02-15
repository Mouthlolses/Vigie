package com.seuvigie.data.di

import com.seuvigie.data.repository.UserRepositoryImpl
import com.seuvigie.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUseRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}