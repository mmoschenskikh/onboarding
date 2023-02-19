package ru.rosmolodez.onboarding.logic

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import ru.rosmolodez.onboarding.logic.Helper.Eff
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.tea.IStatelessFeature
import ru.rosmolodez.onboarding.tg.keyboards.PayloadType

typealias HelperFeature = IStatelessFeature<Msg, Eff>

object Helper {
    sealed class Msg {
        data class OnTgMessageReceived(val message: Message) : Msg()
        data class OnCallbackReceived(val callback: CallbackQuery) : Msg()

        data class OnApiActionSuccess(val chatId: Long, val messageId: Int, val payload: PayloadType) : Msg()
        data class OnApiActionError(val chatId: Long, val messageId: Int) : Msg()
    }

    sealed class Eff {
        sealed class Tg : Eff() {
            data class SendInitialMessage(val chatId: Long) : Tg()
            data class EditMessage(val chatId: Long, val messageId: Int, val payload: PayloadType) : Tg()
        }

        sealed class KnowledgeBaseApi : Eff() {
            data class GetCategories(val chatId: Long, val messageId: Int) : KnowledgeBaseApi()
            data class GetArticles(val chatId: Long, val messageId: Int, val categoryId: Long) : KnowledgeBaseApi()
            data class GetArticle(val chatId: Long, val messageId: Int, val articleId: Long) : KnowledgeBaseApi()
        }

        sealed class ToolsApi : Eff() {
            data class GetGuestWifiPassword(val chatId: Long, val messageId: Int) : ToolsApi()
        }
    }
}
