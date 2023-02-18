package ru.rosmolodez.onboarding.api.config

import ru.rosmolodez.onboarding.api.ApiConst

data class ServerConfig(
    val host: String,
    val basePath: String?,
)

internal val defaultServerConfig = ServerConfig(
    host = ApiConst.HOST,
    basePath = ApiConst.BASE_PATH,
)
