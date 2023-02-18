package ru.rosmolodez.onboarding.model.local

data class Article(
    val id: Long,
    val title: String,
    val text: String,
    val category: Category,
)
