package io.newm.kogmios.exception

import io.newm.kogmios.protocols.messages.JsonWspFault
import java.io.IOException

class KogmiosException(message: String, cause: Throwable, val jsonWspFault: JsonWspFault) : IOException(message, cause)
