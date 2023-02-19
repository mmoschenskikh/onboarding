package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.KeyboardItem
import ru.rosmolodez.onboarding.tg.callbacks.CategoriesCallback
import ru.rosmolodez.onboarding.tg.callbacks.WifiMenuCallback
import ru.rosmolodez.onboarding.utils.singleToList

data class InitialPayload(val text: String? = null) : PayloadType() {

    override val payload = EditMessagePayload(
        text = text,
        items = listOf(
            KeyboardItem(
                name = "📚 База знаний",
                callback = CategoriesCallback,
            ).singleToList(),
            KeyboardItem(
                name = "📡 Wi-Fi",
                callback = WifiMenuCallback,
            ).singleToList(),
        ),
    )
}
