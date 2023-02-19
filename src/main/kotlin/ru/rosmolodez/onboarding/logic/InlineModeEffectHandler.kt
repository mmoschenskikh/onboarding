package ru.rosmolodez.onboarding.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.rosmolodez.onboarding.logic.Helper.Eff
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.tea.SimpleEffectHandler
import ru.rosmolodez.onboarding.tg.keyboards.PayloadType
import ru.rosmolodez.onboarding.utils.tryOrNull

abstract class InlineModeEffectHandler : SimpleEffectHandler<Eff, Msg>() {

    protected abstract val scope: CoroutineScope

    protected fun <T> getEntity(
        chatId: Long,
        messageId: Int,
        listener: (Msg) -> Unit,
        entityGetter: suspend () -> T?,
        payloadProvider: (T) -> PayloadType,
    ) = scope.launch {
        val entity = tryOrNull { withContext(Dispatchers.IO) { entityGetter.invoke() } }
        val msg = if (entity == null) {
            Msg.OnApiActionError(chatId, messageId)
        } else {
            Msg.OnApiActionSuccess(chatId, messageId, payloadProvider.invoke(entity))
        }
        listener.invoke(msg)
    }
}
