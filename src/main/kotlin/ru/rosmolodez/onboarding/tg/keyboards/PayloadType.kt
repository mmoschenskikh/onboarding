package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.tg.EditMessagePayload

sealed class PayloadType {
    abstract val payload: EditMessagePayload
}
