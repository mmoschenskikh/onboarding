package ru.rosmolodez.onboarding.tg.callbacks

object WifiMenuCallback : Callback(), CallbackMatcher<WifiMenuCallback> {

    override val label = "WIFI_MENU"

    override fun tryBuild(label: String) = if (label == this.label) {
        WifiMenuCallback
    } else {
        null
    }
}
