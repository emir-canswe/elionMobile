package com.elion.assistant.data.repository

import com.elion.assistant.data.local.database.dao.CategoryDao
import com.elion.assistant.data.local.database.toDomain
import com.elion.assistant.data.local.database.toEntity
import com.elion.assistant.domain.model.Category
import com.elion.assistant.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories().map { list -> list.map { it.toDomain() } }

    override suspend fun insertCategory(category: Category): Long =
        categoryDao.insertCategory(category.toEntity())

    override suspend fun updateCategory(category: Category) =
        categoryDao.updateCategory(category.toEntity())

    override suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(category.toEntity())

    override suspend fun getCategoryById(id: Long): Category? =
        categoryDao.getCategoryById(id)?.toDomain()

    override suspend fun seedDefaultCategories() {
        val count = categoryDao.count()
        if (count == 0) {
            val entities = Category.defaults.map { it.toEntity() }
            categoryDao.insertAll(entities)
        }
    }
}
