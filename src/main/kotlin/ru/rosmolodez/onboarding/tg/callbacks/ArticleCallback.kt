package ru.rosmolodez.onboarding.tg.callbacks

data class ArticleCallback(val articleId: Long) : Callback() {

    override val label = "${ARTICLE_LABEL}$UNDERSCORE$articleId"

    companion object : CallbackMatcher<ArticleCallback> {

        private const val ARTICLE_LABEL = "ARTICLE"

        override fun tryBuild(label: String): ArticleCallback? {
            val split = label.split(UNDERSCORE)
            if (split.size != 2 ||
                split[0] != ARTICLE_LABEL ||
                split[1].toLongOrNull() == null
            ) return null
            return ArticleCallback(split[1].toLong())
        }
    }
}
