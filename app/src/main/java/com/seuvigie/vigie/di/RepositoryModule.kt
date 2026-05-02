package com.seuvigie.vigie.di

import com.seuvigie.data.repository.AuthRepositoryImpl
import com.seuvigie.data.repository.BillRepositoryImpl
import com.seuvigie.data.repository.RegisterRepositoryImpl
import com.seuvigie.domain.repository.AuthRepository
import com.seuvigie.domain.repository.BillRepository
import com.seuvigie.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository

    @Binds
    @Singleton
    abstract fun bindBillRepository(
        impl: BillRepositoryImpl
    ): BillRepository
}