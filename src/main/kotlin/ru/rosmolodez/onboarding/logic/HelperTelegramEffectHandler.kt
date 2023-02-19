package ru.rosmolodez.onboarding.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.telegram.telegrambots.meta.api.objects.Message
import ru.rosmolodez.onboarding.logic.Helper.Eff
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.tea.Cancelable
import ru.rosmolodez.onboarding.tea.SimpleEffectHandler
import ru.rosmolodez.onboarding.tea.toCancelable
import ru.rosmolodez.onboarding.tg.EditMessagePayload
import java.io.Serializable

class HelperTelegramEffectHandler(
    private val scope: CoroutineScope,
    private val botAsyncInteractionsProvider: () -> HelperBotAsyncInteractions?,
) : SimpleEffectHandler<Eff, Msg>() {

    private val botInteractions
        get() = botAsyncInteractionsProvider.invoke()

    override fun invoke(eff: Eff, listener: (Msg) -> Unit): Cancelable = when (eff) {
        !is Eff.Tg -> Job()
        is Eff.Tg.SendInitialMessage -> sendInitialMessage(eff)
        is Eff.Tg.EditMessage -> editMessage(eff)
    }.toCancelable()

    private fun sendInitialMessage(eff: Eff.Tg.SendInitialMessage) = scope.launch {
        botInteractions
            ?.sendInitialMessageAsync(eff.chatId)
            ?.await()
    }

    private fun editMessage(eff: Eff.Tg.EditMessage) = scope.launch {
        botInteractions
            ?.editMessageAsync(eff.chatId, eff.messageId, eff.payload.payload)
            ?.await()
    }
}

interface HelperBotAsyncInteractions {
    fun sendInitialMessageAsync(chatId: Long): Deferred<Message>
    fun editMessageAsync(chatId: Long, messageId: Int, payload: EditMessagePayload): Deferred<Serializable>
}
