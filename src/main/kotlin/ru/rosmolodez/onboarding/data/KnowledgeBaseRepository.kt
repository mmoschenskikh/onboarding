package ru.rosmolodez.onboarding.data

import ru.rosmolodez.onboarding.api.IKnowledgeBaseApi
import ru.rosmolodez.onboarding.model.converter.ArticleConverter
import ru.rosmolodez.onboarding.model.converter.CategoryConverter
import ru.rosmolodez.onboarding.model.local.Article
import ru.rosmolodez.onboarding.model.local.Category

class KnowledgeBaseRepository(
    private val api: IKnowledgeBaseApi,
) {

    suspend fun getCategories(): List<Category> {
        val response = api.getCategories()
        return response.mapNotNull { CategoryConverter.fromNetwork(it) }
    }

    suspend fun getArticles(categoryId: Long): List<Article> {
        val response = api.getArticles(categoryId)
        return response.mapNotNull { ArticleConverter.fromNetwork(it) }
    }

    suspend fun getArticle(id: Long): Article? {
        val response = api.getArticle(id)
        return ArticleConverter.fromNetwork(response)
    }
}
