package com.template.composehiltstarter.di

import com.template.composehiltstarter.core.dispatcher.DefaultDispatchers
import com.template.composehiltstarter.core.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import timber.log.Timber

/**
 * Hilt module providing the coroutine dispatchers used across the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    /** Provides the production [DispatcherProvider]; tests replace it with test dispatchers. */
    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        Timber.d("Providing DefaultDispatchers as DispatcherProvider")
        return DefaultDispatchers()
    }
}