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
        client.get("categories").body()

    override suspend fun getArticles(id: Long): List<NWArticle> =
        client.get("articles") {
            url.parameters.append("category", id.toString())
        }.body()

    override suspend fun getArticle(id: Long): NWArticle =
        client.get("article/$id").body()
}
