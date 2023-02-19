package ru.rosmolodez.onboarding.tg.callbacks

object CategoriesCallback : Callback(), CallbackMatcher<CategoriesCallback> {

    override val label = "CATEGORIES"

    override fun tryBuild(label: String) = if (label == this.label) {
        CategoriesCallback
    } else {
        null
    }
}
