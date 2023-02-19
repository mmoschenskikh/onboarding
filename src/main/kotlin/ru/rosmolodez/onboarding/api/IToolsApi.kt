package ru.rosmolodez.onboarding.api

interface IToolsApi {

    suspend fun getGuestWifiPassword(): String?
}
