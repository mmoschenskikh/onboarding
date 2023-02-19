package ru.rosmolodez.onboarding.tg

enum class Command(val label: String) {
    START("/start"),
}

object CommandMatcher {

    private val commands = Command.values()

    fun String.asBotCommand(): Command? = commands.firstOrNull { it.label == this }
}
