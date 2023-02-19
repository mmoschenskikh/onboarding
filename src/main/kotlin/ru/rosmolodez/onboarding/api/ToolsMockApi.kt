package ru.rosmolodez.onboarding.api

import java.util.*

/* It is a mock for testing purposes. No actual passwords are stored here. */
object ToolsMockApi : IToolsApi {

    override suspend fun getGuestWifiPassword(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val passwords = listOf(
            "kislovodsk",
            "svetlograd",
            "dolgoprudny",
            "ekaterinburg",
            "vladikavkaz",
            "novosibirsk",
            "vladivostok",
        )
        return passwords[hour % passwords.size]
    }
}
