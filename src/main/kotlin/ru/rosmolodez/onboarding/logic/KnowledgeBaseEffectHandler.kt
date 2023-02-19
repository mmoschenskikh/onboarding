package ru.rosmolodez.onboarding.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import ru.rosmolodez.onboarding.data.KnowledgeBaseRepository
import ru.rosmolodez.onboarding.logic.Helper.Eff
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.tea.Cancelable
import ru.rosmolodez.onboarding.tea.toCancelable
import ru.rosmolodez.onboarding.tg.keyboards.ArticlePayload
import ru.rosmolodez.onboarding.tg.keyboards.CategoriesPayload
import ru.rosmolodez.onboarding.tg.keyboards.CategoryPayload
import ru.rosmolodez.onboarding.utils.nullIfEmpty

class KnowledgeBaseEffectHandler(
    override val scope: CoroutineScope,
    private val repository: KnowledgeBaseRepository,
) : InlineModeEffectHandler() {

    override fun invoke(eff: Eff, listener: (Msg) -> Unit): Cancelable = when (eff) {
        !is Eff.KnowledgeBaseApi -> Job()
        is Eff.KnowledgeBaseApi.GetCategories -> getEntity(
            chatId = eff.chatId,
            messageId = eff.messageId,
            listener = listener,
            entityGetter = { repository.getCategories().nullIfEmpty() },
            payloadProvider = { CategoriesPayload(it) },
        )

        is Eff.KnowledgeBaseApi.GetArticles -> getEntity(
            chatId = eff.chatId,
            messageId = eff.messageId,
            listener = listener,
            entityGetter = { repository.getArticles(eff.categoryId).nullIfEmpty() },
            payloadProvider = { CategoryPayload(it) },
        )

        is Eff.KnowledgeBaseApi.GetArticle -> getEntity(
            chatId = eff.chatId,
            messageId = eff.messageId,
            listener = listener,
            entityGetter = { repository.getArticle(eff.articleId) },
            payloadProvider = { ArticlePayload(it) },
        )
    }.toCancelable()
}
