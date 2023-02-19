package ru.rosmolodez.onboarding.tg.callbacks

object InitialCallback : Callback(), CallbackMatcher<InitialCallback> {

    override val label = "INITIAL"

    override fun tryBuild(label: String) = if (label == this.label) {
        InitialCallback
    } else {
        null
    }
}
