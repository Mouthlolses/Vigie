package com.seuvigie.data.di

import com.seuvigie.data.remoteDataSource.AuthRemoteDataSource
import com.seuvigie.data.remoteDataSource.AuthRemoteDataSourceImpl
import com.seuvigie.data.remoteDataSource.RegisterRemoteDataSource
import com.seuvigie.data.remoteDataSource.RegisterRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        impl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRegisterRemoteDataSource(
        impl: RegisterRemoteDataSourceImpl
    ): RegisterRemoteDataSource
}