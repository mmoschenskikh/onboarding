package ru.rosmolodez.onboarding.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import ru.rosmolodez.onboarding.data.ToolsRepository
import ru.rosmolodez.onboarding.logic.Helper.Eff
import ru.rosmolodez.onboarding.logic.Helper.Msg
import ru.rosmolodez.onboarding.tea.Cancelable
import ru.rosmolodez.onboarding.tea.toCancelable
import ru.rosmolodez.onboarding.tg.keyboards.GuestWifiPayload
import ru.rosmolodez.onboarding.utils.nullIfBlank

class ToolsEffectHandler(
    override val scope: CoroutineScope,
    private val repository: ToolsRepository,
) : InlineModeEffectHandler() {

    override fun invoke(eff: Eff, listener: (Msg) -> Unit): Cancelable = when (eff) {
        !is Eff.ToolsApi -> Job()
        is Eff.ToolsApi.GetGuestWifiPassword -> getEntity(
            chatId = eff.chatId,
            messageId = eff.messageId,
            listener = listener,
            entityGetter = { repository.getGuestWifiPassword()?.nullIfBlank() },
            payloadProvider = { password -> GuestWifiPayload(password) }
        )
    }.toCancelable()
}
