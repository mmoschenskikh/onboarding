package ru.rosmolodez.onboarding.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import ru.rosmolodez.onboarding.api.IKnowledgeBaseApi
import ru.rosmolodez.onboarding.api.IToolsApi
import ru.rosmolodez.onboarding.api.KnowledgeBaseApi
import ru.rosmolodez.onboarding.api.ToolsMockApi
import ru.rosmolodez.onboarding.api.config.ServerConfig

class ApiProvider(private val config: ServerConfig) {

    private val httpClient by lazy {
        HttpClient(CIO) {
            install(DefaultRequest)
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTP
                    host = config.host
                    config.basePath?.let { path(it) }
                }
            }
        }
    }

    val knowledgeBaseApi: IKnowledgeBaseApi by lazy { KnowledgeBaseApi(httpClient) }
    val toolsApi: IToolsApi by lazy { ToolsMockApi }
}
