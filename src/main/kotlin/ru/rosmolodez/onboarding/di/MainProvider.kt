package ru.rosmolodez.onboarding.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.rosmolodez.onboarding.api.config.defaultServerConfig
import ru.rosmolodez.onboarding.data.KnowledgeBaseRepository
import ru.rosmolodez.onboarding.data.ToolsRepository
import ru.rosmolodez.onboarding.tg.HelperBot

class MainProvider {

    private val backgroundWorkScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val apiProvider by lazy { ApiProvider(defaultServerConfig) }
    private val knowledgeBaseApi by lazy { apiProvider.knowledgeBaseApi }
    private val toolsApi by lazy { apiProvider.toolsApi }

    private val knowledgeBaseRepository by lazy { KnowledgeBaseRepository(knowledgeBaseApi) }
    private val toolsRepository by lazy { ToolsRepository(toolsApi) }

    private val callbacksProvider by lazy { CallbacksProvider() }

    private val helperProvider: HelperProvider by lazy {
        HelperProvider(
            backgroundWorkScope = backgroundWorkScope,
            botAsyncInteractionsProvider = { bot },
            knowledgeBaseRepository = knowledgeBaseRepository,
            toolsRepository = toolsRepository,
            callbackParser = callbacksProvider.parser,
        )
    }

    private val helperFeature by lazy { helperProvider.feature }

    val bot by lazy { HelperBot(helperFeature) }
}
