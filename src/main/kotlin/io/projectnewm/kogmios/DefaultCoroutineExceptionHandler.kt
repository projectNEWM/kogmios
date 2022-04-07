package io.projectnewm.kogmios

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import org.slf4j.Logger
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class DefaultCoroutineExceptionHandler(
    private val log: Logger
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        if (exception !is CancellationException) {
            log.error("Unhandled Coroutine Exception!", exception)
        }
    }
}
