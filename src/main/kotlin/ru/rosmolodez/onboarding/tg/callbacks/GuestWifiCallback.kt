package ru.rosmolodez.onboarding.tg.callbacks

object GuestWifiCallback : Callback(), CallbackMatcher<GuestWifiCallback> {

    override val label = "GUEST_WIFI"

    override fun tryBuild(label: String) = if (label == this.label) {
        GuestWifiCallback
    } else {
        null
    }
}
