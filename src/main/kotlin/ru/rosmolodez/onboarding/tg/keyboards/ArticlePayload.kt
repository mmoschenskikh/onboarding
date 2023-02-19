package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.model.local.Article
import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.callbacks.CategoryCallback

data class ArticlePayload(val article: Article) : PayloadType() {

    override val payload = EditMessagePayload(
        text = "${article.title}\n\n${article.text}",
        items = listOf(
            backDirectionKeys(CategoryCallback(article.category.id)),
        ),
    )
}
