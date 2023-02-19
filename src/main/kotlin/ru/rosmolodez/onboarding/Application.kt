package ru.rosmolodez.onboarding

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.rosmolodez.onboarding.di.MainProvider

fun main() {
    val provider = MainProvider()
    val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
    botsApi.registerBot(provider.bot)
}
