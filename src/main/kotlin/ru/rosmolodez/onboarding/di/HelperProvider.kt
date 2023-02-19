package ru.rosmolodez.onboarding.di

import kotlinx.coroutines.CoroutineScope
import ru.rosmolodez.onboarding.data.KnowledgeBaseRepository
import ru.rosmolodez.onboarding.data.ToolsRepository
import ru.rosmolodez.onboarding.logic.HelperBotAsyncInteractions
import ru.rosmolodez.onboarding.logic.HelperReducer
import ru.rosmolodez.onboarding.logic.HelperTelegramEffectHandler
import ru.rosmolodez.onboarding.logic.KnowledgeBaseEffectHandler
import ru.rosmolodez.onboarding.logic.ToolsEffectHandler
import ru.rosmolodez.onboarding.tea.StatelessFeature
import ru.rosmolodez.onboarding.tea.wrapWithEffectHandler
import ru.rosmolodez.onboarding.tg.callbacks.CallbackParser

class HelperProvider(
    backgroundWorkScope: CoroutineScope,
    botAsyncInteractionsProvider: () -> HelperBotAsyncInteractions?,
    knowledgeBaseRepository: KnowledgeBaseRepository,
    toolsRepository: ToolsRepository,
    callbackParser: CallbackParser,
) {

    private val reducer = HelperReducer(callbackParser)

    val feature = StatelessFeature(
        reducer = reducer::reduce,
    ).wrapWithEffectHandler(
        effectHandler = HelperTelegramEffectHandler(
            scope = backgroundWorkScope,
            botAsyncInteractionsProvider = botAsyncInteractionsProvider,
        )
    ).wrapWithEffectHandler(
        effectHandler = KnowledgeBaseEffectHandler(
            scope = backgroundWorkScope,
            repository = knowledgeBaseRepository,
        )
    ).wrapWithEffectHandler(
        effectHandler = ToolsEffectHandler(
            scope = backgroundWorkScope,
            repository = toolsRepository,
        )
    )
}
