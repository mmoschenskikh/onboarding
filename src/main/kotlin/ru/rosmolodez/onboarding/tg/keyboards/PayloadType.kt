package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.KeyboardItem
import ru.rosmolodez.onboarding.tg.callbacks.Callback
import ru.rosmolodez.onboarding.tg.callbacks.InitialCallback

sealed class PayloadType {
    abstract val payload: EditMessagePayload
}

fun backDirectionKeys(backCallback: Callback) =
    listOf(
        KeyboardItem(
            name = "⤴️ В начало",
            callback = InitialCallback,
        ),
        KeyboardItem(
            name = "⬅️ Назад",
            callback = backCallback,
        ),
    )
