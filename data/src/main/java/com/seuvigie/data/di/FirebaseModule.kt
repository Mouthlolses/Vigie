package com.seuvigie.data.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {


    @Provides
    @Singleton
    fun provideFirebase(
        @ApplicationContext context: Context
    ): FirebaseApp {
        return FirebaseApp.initializeApp(context)!!

    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(
        firebaseApp: FirebaseApp
    ) : FirebaseAuth {
        return FirebaseAuth.getInstance(firebaseApp)
    }

}