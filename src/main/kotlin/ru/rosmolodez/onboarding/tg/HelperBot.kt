package ru.rosmolodez.onboarding.tg

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.future.asDeferred
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.logic.HelperBotAsyncInteractions
import ru.rosmolodez.onboarding.logic.HelperFeature
import ru.rosmolodez.onboarding.tg.keyboards.InitialPayload
import java.io.Serializable

private const val POST_IMAGE_URL = "https://imgur.com/a/LcxJrcv"

class HelperBot(
    private val feature: HelperFeature,
) : TelegramLongPollingBot(TELEGRAM_BOT_TOKEN), HelperBotAsyncInteractions {

    override fun getBotUsername() = TELEGRAM_BOT_USERNAME

    override fun onUpdateReceived(update: Update?) {
        val message = update?.message
        val callback = update?.callbackQuery
        when {
            message != null -> feature.accept(Msg.OnTgMessageReceived(message))
            callback != null -> feature.accept(Msg.OnCallbackReceived(callback))
        }
    }

    override fun sendInitialMessageAsync(chatId: Long): Deferred<Message> {
        val payload = InitialPayload().payload

        val keyboardMarkup = InlineKeyboardMarkup.builder()
            .keyboard(payload.items.toTgInlineKeyboard())
            .build()

        val messageCommand = SendPhoto.builder()
            .chatId(chatId)
            .photo(InputFile(POST_IMAGE_URL))
            .replyMarkup(keyboardMarkup)
            .build()

        return executeAsync(messageCommand).asDeferred()
    }

    override fun editMessageAsync(chatId: Long, messageId: Int, payload: EditMessagePayload): Deferred<Serializable> {
        val keyboardMarkup = InlineKeyboardMarkup.builder()
            .keyboard(payload.items.toTgInlineKeyboard())
            .build()

        val msg = EditMessageCaption.builder()
            .chatId(chatId)
            .caption(payload.text)
            .messageId(messageId)
            .replyMarkup(keyboardMarkup)
            .build()
        return executeAsync(msg).asDeferred()
    }


}
