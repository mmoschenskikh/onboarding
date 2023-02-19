package ru.rosmolodez.onboarding.logic

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import ru.rosmolodez.onboarding.logic.Helper.Eff
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.tg.Command
import ru.rosmolodez.onboarding.tg.CommandMatcher.asBotCommand
import ru.rosmolodez.onboarding.tg.callbacks.ArticleCallback
import ru.rosmolodez.onboarding.tg.callbacks.CallbackParser
import ru.rosmolodez.onboarding.tg.callbacks.CategoriesCallback
import ru.rosmolodez.onboarding.tg.callbacks.CategoryCallback
import ru.rosmolodez.onboarding.tg.callbacks.GuestWifiCallback
import ru.rosmolodez.onboarding.tg.callbacks.InitialCallback
import ru.rosmolodez.onboarding.tg.callbacks.UnknownCallback
import ru.rosmolodez.onboarding.tg.callbacks.WifiMenuCallback
import ru.rosmolodez.onboarding.tg.keyboards.InitialPayload
import ru.rosmolodez.onboarding.tg.keyboards.PayloadType
import ru.rosmolodez.onboarding.tg.keyboards.WifiMenuPayload
import ru.rosmolodez.onboarding.utils.singleToSet

class HelperReducer(
    private val callbackParser: CallbackParser,
) {

    fun reduce(msg: Msg): Set<Eff> = when (msg) {
        is Msg.OnTgMessageReceived -> handleMessageReceived(msg.message)
        is Msg.OnCallbackReceived -> handleCallbackReceived(msg.callback)
        is Msg.OnApiActionSuccess -> handleApiActionSuccess(msg.chatId, msg.messageId, msg.payload)
        is Msg.OnApiActionError -> handleApiActionError(msg.chatId, msg.messageId)
    }

    private fun handleMessageReceived(message: Message): Set<Eff> {
        val command = message.text?.asBotCommand()
        return if (command != null) {
            handleBotCommand(message.chatId, command)
        } else {
            ignore()
        }
    }

    private fun handleCallbackReceived(callbackQuery: CallbackQuery): Set<Eff> {
        val chatId = callbackQuery.message.chatId
        val messageId = callbackQuery.message.messageId
        return when (val callback = callbackParser.parse(callbackQuery.data)) {
            InitialCallback -> Eff.Tg.EditMessage(chatId, messageId, InitialPayload()).singleToSet()
            CategoriesCallback -> Eff.KnowledgeBaseApi.GetCategories(chatId, messageId).singleToSet()
            is CategoryCallback -> Eff.KnowledgeBaseApi.GetArticles(chatId, messageId, callback.categoryId).singleToSet()
            is ArticleCallback -> Eff.KnowledgeBaseApi.GetArticle(chatId, messageId, callback.articleId).singleToSet()
            WifiMenuCallback -> Eff.Tg.EditMessage(chatId, messageId, WifiMenuPayload).singleToSet()
            is GuestWifiCallback -> Eff.ToolsApi.GetGuestWifiPassword(chatId, messageId).singleToSet()
            UnknownCallback -> ignore()
        }
    }

    private fun handleApiActionSuccess(chatId: Long, messageId: Int, payload: PayloadType) =
        Eff.Tg.EditMessage(chatId, messageId, payload).singleToSet()

    private fun handleApiActionError(chatId: Long, messageId: Int) =
        Eff.Tg.EditMessage(
            chatId = chatId,
            messageId = messageId,
            payload = InitialPayload("Ошибка при загрузке данных.\nПопробуйте позже.")
        ).singleToSet()

    private fun handleBotCommand(chatId: Long, command: Command) = when (command) {
        Command.START -> Eff.Tg.SendInitialMessage(chatId).singleToSet()
    }

    private fun ignore() = emptySet<Eff>()
}
