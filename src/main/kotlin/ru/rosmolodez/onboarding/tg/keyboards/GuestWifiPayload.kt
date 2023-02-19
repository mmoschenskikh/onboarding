package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.callbacks.WifiMenuCallback

data class GuestWifiPayload(val password: String) : PayloadType() {

    override val payload = EditMessagePayload(
        text = "Пароль от гостевого Wi-Fi:\n$password",
        items = listOf(
            backDirectionKeys(WifiMenuCallback),
        ),
    )
}
