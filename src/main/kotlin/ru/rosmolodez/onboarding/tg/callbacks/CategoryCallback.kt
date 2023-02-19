package ru.rosmolodez.onboarding.tg.callbacks

data class CategoryCallback(val categoryId: Long) : Callback() {

    override val label = "${CATEGORY_LABEL}$UNDERSCORE$categoryId"

    companion object : CallbackMatcher<CategoryCallback> {

        private const val CATEGORY_LABEL = "CATEGORY"

        override fun tryBuild(label: String): CategoryCallback? {
            val split = label.split(UNDERSCORE)
            if (split.size != 2 ||
                split[0] != CATEGORY_LABEL ||
                split[1].toLongOrNull() == null
            ) return null
            return CategoryCallback(split[1].toLong())
        }
    }
}
