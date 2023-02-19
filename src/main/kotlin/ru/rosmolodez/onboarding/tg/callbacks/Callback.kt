package ru.rosmolodez.onboarding.tg.callbacks

const val UNDERSCORE = "_"

sealed class Callback {
    abstract val label: String
}

interface CallbackMatcher<T : Callback> {
    fun tryBuild(label: String): T?
}

class CallbackParser(private val matchers: List<CallbackMatcher<out Callback>>) {
    fun parse(label: String?): Callback {
        if (label == null) return UnknownCallback
        for (matcher in matchers) {
            val parsedCallback = matcher.tryBuild(label)
            if (parsedCallback != null) return parsedCallback
        }
        return UnknownCallback
    }
}
