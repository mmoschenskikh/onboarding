package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.KeyboardItem
import ru.rosmolodez.onboarding.tg.callbacks.GuestWifiCallback
import ru.rosmolodez.onboarding.tg.callbacks.InitialCallback
import ru.rosmolodez.onboarding.utils.singleToList

object WifiMenuPayload : PayloadType() {

    override val payload = EditMessagePayload(
        text = null,
        items = listOf(
            KeyboardItem(
                name = "🙎 Гостевой",
                callback = GuestWifiCallback,
            ).singleToList(),
            backDirectionKeys(InitialCallback),
        ),
    )
}
