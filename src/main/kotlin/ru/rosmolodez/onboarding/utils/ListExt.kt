package ru.rosmolodez.onboarding.utils

fun <T> T.singleToList() = listOf(this)

fun <T> List<T>.nullIfEmpty() = ifEmpty { null }
