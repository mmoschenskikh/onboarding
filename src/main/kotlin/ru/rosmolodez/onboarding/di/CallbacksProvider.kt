package ru.rosmolodez.onboarding.di

import ru.rosmolodez.onboarding.tg.callbacks.ArticleCallback
import ru.rosmolodez.onboarding.tg.callbacks.CallbackParser
import ru.rosmolodez.onboarding.tg.callbacks.CategoriesCallback
import ru.rosmolodez.onboarding.tg.callbacks.CategoryCallback
import ru.rosmolodez.onboarding.tg.callbacks.GuestWifiCallback
import ru.rosmolodez.onboarding.tg.callbacks.InitialCallback
import ru.rosmolodez.onboarding.tg.callbacks.WifiMenuCallback

class CallbacksProvider {

    private val matchers = listOf(
        InitialCallback,
        CategoriesCallback,
        CategoryCallback,
        ArticleCallback,
        WifiMenuCallback,
        GuestWifiCallback,
    )

    val parser = CallbackParser(matchers)
}
