package ru.rosmolodez.onboarding.model.converter

import ru.rosmolodez.onboarding.model.local.Category
import ru.rosmolodez.onboarding.model.network.NWCategory
import ru.rosmolodez.onboarding.utils.let

object CategoryConverter {

    fun fromNetwork(src: NWCategory): Category? = let(src.id, src.name) { id: Long, name: String ->
        Category(
            id = id,
            name = name,
        )
    }
}
