package ru.rosmolodez.onboarding.data

import ru.rosmolodez.onboarding.api.IToolsApi
import ru.rosmolodez.onboarding.utils.nullIfBlank

class ToolsRepository(
    private val api: IToolsApi,
) {

    suspend fun getGuestWifiPassword(): String? {
        val response = api.getGuestWifiPassword()
        return response?.nullIfBlank()
    }
}
