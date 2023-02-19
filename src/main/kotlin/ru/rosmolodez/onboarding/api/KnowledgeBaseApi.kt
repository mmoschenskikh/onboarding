package ru.rosmolodez.onboarding.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.rosmolodez.onboarding.model.network.NWArticle
import ru.rosmolodez.onboarding.model.network.NWCategory

class KnowledgeBaseApi(
    private val client: HttpClient,
) : IKnowledgeBaseApi {

    override suspend fun getCategories(): List<NWCategory> =
        client.get("category").body()

    override suspend fun getArticles(categoryId: Long): List<NWArticle> =
        client.get("article/search/") {
            url.parameters.append("category", categoryId.toString())
        }.body()

    override suspend fun getArticle(id: Long): NWArticle =
        client.get("article/$id").body()
}
