package ru.rosmolodez.onboarding.tg.keyboards

import ru.rosmolodez.onboarding.model.local.Category
import ru.rosmolodez.onboarding.tg.EditMessagePayload
import ru.rosmolodez.onboarding.tg.KeyboardItem
import ru.rosmolodez.onboarding.tg.callbacks.CategoryCallback
import ru.rosmolodez.onboarding.tg.callbacks.InitialCallback
import ru.rosmolodez.onboarding.utils.singleToList

data class CategoriesPayload(val categories: List<Category>) : PayloadType() {

    override val payload = EditMessagePayload(
        text = "Выберите категорию",
        items = categories.map { category ->
            KeyboardItem(
                name = category.name,
                callback = CategoryCallback(category.id),
            ).singleToList()
        }.plus(
            listOf(
                backDirectionKeys(InitialCallback),
            )
        )
    )
}
