package ru.rosmolodez.onboarding.tea

import kotlinx.coroutines.Job

interface Cancelable {
    fun cancel()
}

fun Job.toCancelable() = object : Cancelable {
    override fun cancel() {
        this@toCancelable.cancel()
    }
}
