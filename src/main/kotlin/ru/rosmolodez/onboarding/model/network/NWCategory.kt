package ru.rosmolodez.onboarding.model.network

import kotlinx.serialization.Serializable

@Serializable
@Suppress("UNUSED")
data class NWCategory(
    val id: Long? = null,
    val name: String? = null,
)
