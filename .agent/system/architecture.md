# 🏗️ Kogmios System Architecture

> **Source of truth for system-wide architectural decisions.**

This document provides the definitive overview of the Kogmios architecture, module layout, and core design patterns.

---

## System Overview

Kogmios is a Kotlin client for the Ogmios WebSocket protocol. It wraps JSON-WSP requests/responses into typed Kotlin models and exposes coroutine-based APIs for four logical protocols.

```
┌────────────────────────────────────────────────────────────────────┐
│                           Client Application                       │
├────────────────────────────────────────────────────────────────────┤
│                                                                    │
│  Kogmios Clients (Kotlin)                                          │
│  ┌────────────┐  ┌───────────┐  ┌───────────┐  ┌───────────┐       │
│  │ StateQuery │  │ TxSubmit  │  │ TxMonitor │  │ ChainSync │       │
│  └─────┬──────┘  └─────┬─────┘  └─────┬─────┘  └─────┬─────┘       │
│        │ JSON-WSP over WebSocket      │              │             │
└────────┼─────────────────────────── ──┼──────────────┼─────────────┘
         ▼                              ▼              ▼
    ┌─────────────────────────────────────────────────────────┐
    │                   Ogmios WebSocket Server               │
    └─────────────────────────────────────────────────────────┘
                        │
                        ▼
                  Cardano Node
```

---

## Module Reference

### Core Module

| Module | Package | Responsibility |
|--------|---------|----------------|
| `kogmios` | `io.newm.kogmios` | Ogmios client library, protocols, models |

### Package Layout

| Area | Path | Responsibility |
|------|------|----------------|
| Client interfaces | `io.newm.kogmios` | Client APIs for StateQuery/TxSubmit/TxMonitor/ChainSync |
| Protocol messages | `io.newm.kogmios.protocols.messages` | JSON-WSP request/response payloads |
| Protocol models | `io.newm.kogmios.protocols.model` | Typed data structures for protocol data |
| Serialization | `io.newm.kogmios.serializers` | Custom serializers for protocol data |

---

## Core Design Patterns

### Client Interfaces

```kotlin
// Location: io.newm.kogmios/*Client.kt

val client = createStateQueryClient(
    websocketHost = "localhost",
    websocketPort = 1337,
    secure = false,
)

client.use {
    val connected = it.connect()
    require(connected == true) { "Failed to connect" }
    val tip = it.chainTip()
}
```

### Protocol Messages

```kotlin
// Location: io.newm.kogmios.protocols.messages

@Serializable
data class MsgQueryTipResponse(
    val result: QueryTipResult,
    val id: String,
    val jsonrpc: String = JSONRPC_VERSION,
)
```

### Serialization Helpers

```kotlin
// Location: io.newm.kogmios.serializers

object BigIntegerSerializer : KSerializer<BigInteger> {
    override val descriptor = PrimitiveSerialDescriptor("BigInteger", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): BigInteger = BigInteger(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: BigInteger) = encoder.encodeString(value.toString())
}
```

---

## Integration Points

### Ogmios
- **Interface**: JSON-WSP over WebSocket
- **Purpose**: Query UTXOs, submit transactions, monitor chain, sync blocks
- **Default Port**: 1337 (standard Ogmios)

---

## Environment Configuration

### Required Configuration
- Ogmios host + port
- Optional TLS (`secure = true`) when using wss

---

## Build & Publish Overview

See [`/workflows/build.md`](../workflows/build.md) for build and verification commands.

### Quick Reference

```bash
# Build
./gradlew build

# Run tests
./gradlew test
```
