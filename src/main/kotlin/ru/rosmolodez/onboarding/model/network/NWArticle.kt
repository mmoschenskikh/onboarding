package ru.rosmolodez.onboarding.model.network

import kotlinx.serialization.Serializable

@Serializable
@Suppress("UNUSED")
data class NWArticle(
    val id: Long? = null,
    val title: String? = null,
    val text: String? = null,
    val category: NWCategory? = null,
)
