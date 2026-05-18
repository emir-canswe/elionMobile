package com.elion.assistant.di

import com.elion.assistant.data.repository.CategoryRepositoryImpl
import com.elion.assistant.data.repository.StatRepositoryImpl
import com.elion.assistant.data.repository.TaskRepositoryImpl
import com.elion.assistant.domain.repository.CategoryRepository
import com.elion.assistant.domain.repository.StatRepository
import com.elion.assistant.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Binds @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds @Singleton
    abstract fun bindStatRepository(impl: StatRepositoryImpl): StatRepository
}
