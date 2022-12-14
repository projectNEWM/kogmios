# kogmios

Kotlin implementation of Ogmios Client

### Background

The standard Ogmios interface uses a websocket and Json WSP messages. While this is good for exposing the node's
information to a lot of different programming environments, there is a lot of boilerplate code to write in each language
to convert json wsp messages into usable objects. This is where kogmios comes in for the Kotlin language.

The api is broken up into using 4 different logical client types: **StateQuery**, **TxSubmit**, **TxMonitor**, and **
ChainSync**

Where possible, we have tried to abstract away the complexities of network communication and asynchronous responses by
using Kotlin Coroutines. This allows the developer to write what looks like simple imperitive code on top of an
asynchronous api.

## Setup

In order to use Kogmios, you will need to have a running instance of Ogmios pointed at an active instance of
cardano-node. Please refer to the Ogmios documentation for setup instructions.

On the Kogmios side, all you will need is the ip/dns name of the Ogmios server as well as the port it is running on (
default: 1337).

## Usage

Below, you will find a few example usages of the Kogmios api. To find more exhaustive examples, refer to the test code
in this repository.

When you are done using a Kogmios client, you should clean up and call the `shutdown()` method explicitly, OR you can
utilize the client's `Closeable` interface along with Kotlin `.use{}` to ensure clean up happens automatically. This
latter approach is what we will use in the examples.

All methods must be called from inside a Coroutine Context. The client methods will suspend until data returns from the
server, or an exception is thrown. Many calls can be made through Kogmios using the same Client object. Only clean it up
once you are done with it or it has thrown an error. In the case of errors, it is best to create a new instance of the
Client so it can re-connect.

### StateQuery

The **StateQuery** client is used for querying the current (or past) state of the node and blockchain. In most cases,
the api will automatically acquire the tip of the blockchain. You should not need to explicitly call `acquire(point)`
unless you have advanced needs.

#### ChainTip Example:

```kotlin
suspend fun getChainTip(): QueryPointResult {
    createStateQueryClient(websocketHost = "server.domainname.io", websocketPort = 1337).use { client ->
        val connectResult = client.connect()
        require(connectResult == true) { "Failed to connect!" }

        val tipResult = client.chainTip()
        return tipResult.result as QueryPointResult
    }
}
```

### TxSubmit

The **TxSubmit** client is used for submitting transactions and also evaluating costs for smart contract transactions.

#### SubmitTx Example:

```kotlin
createTxSubmitClient(websocketHost = "server.domainname.io", websocketPort = 1337).use { client ->
    val connectResult = client.connect()
    require(connectResult == true) { "Failed to connect!" }

    val response = client.submit(
        "84a4008282582065a390ff705fcf738c7c0fc490ced778f06814f268346618f1c8af2d757696470082582065a390ff705fcf738c7c0fc490ced778f06814f268346618f1c8af2d7576964701018282581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b1a000f424082581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b821b0001ad34bfdbcbcba1581c8d18d786e92776c824607fd8e193ec535c79dc61ea2405ddf3b09fe3a145707266697401021a0002beb1031a03ed1803a100818258204499320a77997987955eadba91721d5be54ca36536c5448009e822ba3f882d695840f36654ed91e88aeba1d5c60956c097621684b321b1605cea5f948d62c8055859ff54e4178ec08d720884d51221f291d7519a4914ad951bf0d356c387f239230bf5f6"
    )
    println(response.result)
}
```

### TxMonitor

```kotlin
createTxMonitorClient(
    websocketHost = "server.domainname.io",
    websocketPort = 443,
    secure = true,
).use { client ->
    val connectResult = client.connect()
    require(connectResult == true) { "Failed to connect!" }

    val response = client.awaitAcquireMempool()
    val response1 = client.hasTx("7f042e1a54b9f699961de8a47543d4c4cef0bc5bc5e406194d9952667e2c077d")
    println("Has Tx: ${response1.result}")
    val response2 = client.releaseMempool()
}
```

### ChainSync

```kotlin
createChainSyncClient(
    websocketHost = "server.domainname.io",
    websocketPort = 443,
    secure = true,
    ogmiosCompact = true,
).use { client ->
    val connectResult = client.connect()
    require(connectResult == true) { "Failed to connect!" }

    val intersectResponse = client.findIntersect(
        listOf(
            // preprod - last byron block
            PointDetail(
                slot = 84242L,
                hash = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af"
            )
        )
    )
    require(intersectResponse.result is IntersectionFound)

    var lastLogged = Instant.EPOCH
    var isTip = false
    while (true) {
        val response = client.requestNext(
            timeoutMs = if (isTip) {
                // if we're on tip, wait forever for next block to arrive
                INFINITE_REQUEST_TIMEOUT_MS
            } else {
                DEFAULT_REQUEST_TIMEOUT_MS
            }
        )
        when (response.result) {
            is RollBackward -> {
                log.info("RollBackward: ${(response.result as RollBackward).rollBackward.point}")
            }

            is RollForward -> {
                (response.result as RollForward).rollForward.let { rollForward ->
                    val blockHeight = rollForward.block.header.blockHeight
                    val tipBlockHeight = max(blockHeight, rollForward.tip.blockNo)
                    isTip = blockHeight == tipBlockHeight
                    // do something with the data
                    processBlock(rollForward.block)
                    val now = Instant.now()
                    val fiveSecondsAgo = now.minusSeconds(5L)
                    if (isTip || fiveSecondsAgo.isAfter(lastLogged)) {
                        val percent = blockHeight.toDouble() / tipBlockHeight.toDouble() * 100.0
                        log.info(
                            "RollForward: block $blockHeight of $tipBlockHeight: %.2f%% sync'd".format(
                                percent
                            )
                        )
                        lastLogged = now
                    }
                }
            }
        }
    }
}
```

## Contributing

[Ktlint]("https://ktlint.github.io/") is used in CI to lint the codebase for every PR.
Before opening a PR, it would be beneficial to run Ktlint locally.

The project contains a pre-commit hook to run Ktlint for every commit. To enable it,
install Ktlint and then run the following command:

`git config core.hooksPath .githooks`
