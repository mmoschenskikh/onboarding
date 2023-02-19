package ru.rosmolodez.onboarding.api

import ru.rosmolodez.onboarding.model.network.NWArticle
import ru.rosmolodez.onboarding.model.network.NWCategory

interface IKnowledgeBaseApi {
    suspend fun getCategories(): List<NWCategory>
    suspend fun getArticles(categoryId: Long): List<NWArticle>
    suspend fun getArticle(id: Long): NWArticle
}
