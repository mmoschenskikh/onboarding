package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.model.local.Article
import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.KeyboardItem
import ru.rosmolodez.onboarding.tg.callbacks.ArticleCallback
import ru.rosmolodez.onboarding.tg.callbacks.CategoriesCallback
import ru.rosmolodez.onboarding.utils.singleToList

data class CategoryPayload(val articles: List<Article>) : PayloadType() {

    override val payload = EditMessagePayload(
        text = "Выберите статью",
        items = articles.map { article ->
            KeyboardItem(
                name = article.title,
                callback = ArticleCallback(article.id),
            ).singleToList()
        }.plus(
            listOf(
                backDirectionKeys(CategoriesCallback),
            )
        )
    )
}
