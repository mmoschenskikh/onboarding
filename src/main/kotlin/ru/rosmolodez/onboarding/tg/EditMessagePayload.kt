package ru.rosmolodez.onboarding.tg

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.rosmolodez.onboarding.tg.callbacks.Callback

typealias Keyboard = List<List<KeyboardItem>>

data class EditMessagePayload(
    val text: String?,
    val items: Keyboard,
)

data class KeyboardItem(
    val name: String,
    val callback: Callback,
)

fun Keyboard.toTgInlineKeyboard() = map { row ->
    row.map { item ->
        InlineKeyboardButton.builder()
            .text(item.name)
            .callbackData(item.callback.label)
            .build()
    }
}
