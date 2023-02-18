package ru.rosmolodez.onboarding.tea

import ru.rosmolodez.onboarding.tea.utils.addListenerAndMakeCancelable
import ru.rosmolodez.onboarding.tea.utils.notifyAll

class StatelessFeature<Msg : Any, Eff : Any>(
    private val reducer: (Msg) -> Set<Eff>,
) : IStatelessFeature<Msg, Eff> {
    private var isCanceled = false
    private val effListeners = mutableListOf<(eff: Eff) -> Unit>()

    override fun accept(msg: Msg) {
        if (isCanceled) return
        val commands = reducer(msg)
        commands.forEach { command ->
            effListeners.notifyAll(command)
        }
    }

    override fun listenEffect(listener: (eff: Eff) -> Unit): Cancelable =
        effListeners.addListenerAndMakeCancelable(listener)

    override fun cancel() {
        isCanceled = true
    }
}
