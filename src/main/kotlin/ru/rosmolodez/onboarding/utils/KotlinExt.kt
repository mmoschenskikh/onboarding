package ru.rosmolodez.onboarding.utils

inline fun <P1, P2, R> let(
    p1: P1?,
    p2: P2?,
    block: (P1, P2) -> R,
): R? = p1?.let { p2?.let { block(p1, p2) } }

inline fun <P1, P2, P3, R> let(
    p1: P1?,
    p2: P2?,
    p3: P3?,
    block: (P1, P2, P3) -> R,
): R? = p3?.let { let(p1, p2) { p1, p2 -> block(p1, p2, p3) } }

inline fun <P1, P2, P3, P4, R> let(
    p1: P1?,
    p2: P2?,
    p3: P3?,
    p4: P4?,
    block: (P1, P2, P3, P4) -> R,
): R? = p4?.let { let(p1, p2, p3) { p1, p2, p3 -> block(p1, p2, p3, p4) } }
