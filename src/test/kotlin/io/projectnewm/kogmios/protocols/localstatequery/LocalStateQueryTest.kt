package io.projectnewm.kogmios.protocols.localstatequery

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.Client
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class LocalStateQueryTest {

    @Test
    fun `test acquire`() = runBlocking {
        val client = Client(websocketHost = "localhost", websocketPort = 1337)
        val result = client.asyncConnect().await()
        assertThat(result).isTrue()
        client.acquire()
        delay(5000)
        //client.disconnect()
    }
}