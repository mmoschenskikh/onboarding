package ru.rosmolodez.onboarding.model.converter

import ru.rosmolodez.onboarding.model.local.Article
import ru.rosmolodez.onboarding.model.network.NWArticle
import ru.rosmolodez.onboarding.utils.let

object ArticleConverter {

    fun fromNetwork(src: NWArticle): Article? = let(
        src.id,
        src.title,
        src.text,
        src.category?.let { CategoryConverter.fromNetwork(it) },
    ) { id, title, text, category ->
        Article(
            id = id,
            title = title,
            text = text,
            category = category,
        )
    }
}
