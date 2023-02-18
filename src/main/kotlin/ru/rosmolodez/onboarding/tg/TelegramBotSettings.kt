package ru.rosmolodez.onboarding.tg

import ru.rosmolodez.onboarding.utils.getEnvOrThrow

internal val TELEGRAM_BOT_TOKEN: String = getEnvOrThrow("TELEGRAM_BOT_TOKEN")
internal val TELEGRAM_BOT_USERNAME: String = getEnvOrThrow("TELEGRAM_BOT_USERNAME")
