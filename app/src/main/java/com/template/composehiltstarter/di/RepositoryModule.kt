package com.template.composehiltstarter.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module binding repository interfaces from `domain/repository` to their implementations in
 * `data/repository`. Add one `@Binds` function per repository.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

}