package ru.rosmolodez.onboarding.utils

fun String.nullIfBlank(): String? = ifBlank { null }
