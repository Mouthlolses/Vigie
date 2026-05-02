package com.seuvigie.vigie.di

import com.seuvigie.data.database.dao.UserDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserLocalDatasource(
        impl: UserDao
    ): UserDao

}