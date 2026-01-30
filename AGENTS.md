# 🤖 Kogmios AI Agent Instructions

> **For all AI coding assistants.** This file provides a starting point for understanding the Kogmios codebase.

---

## Quick Start

**Read the full documentation:** [`.agent/readme.md`](./.agent/readme.md)

The `.agent/` directory contains comprehensive documentation organized for AI agents:

| Folder | Purpose | When to Read |
|--------|---------|--------------|
| `.agent/system/` | Architecture, patterns, publish/build | Understanding system design |
| `.agent/task/` | Past PRDs and implementation plans | Before implementing new features |
| `.agent/SOPs/` | Standard operating procedures | When encountering known issues |
| `.agent/workflows/` | Step-by-step guides | When executing specific tasks |

---

## Available Workflows

Use these slash commands to access workflows:

| Command | Description |
|---------|-------------|
| `/build` | Build the project |
| `/test` | Run test suites |
| `/backend_dependencies` | Check and update dependencies |
| `/update-doc` | Update this documentation |

---

## Skills

Skills are reusable instructions that an agent can load with the `skill` tool.

Current status:
- `git-commit-formatter` — Formats git commit messages to Conventional Commits when asked to commit or draft commit messages. See `.agent/skills/git-commit-formatter/SKILL.md`.

If skills are added, list them in `.agent/readme.md` with a short description and usage guidance.

## Project Overview

**Kogmios** is a Kotlin implementation of an Ogmios client. It wraps Ogmios JSON-WSP messages into typed Kotlin models and exposes coroutine-friendly APIs.

### Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin 2.x on Java 21 |
| Networking | Ktor client (CIO + websockets) |
| Serialization | kotlinx-serialization |
| Async | Kotlin coroutines |
| Build | Gradle (Kotlin DSL) |

---

## Important Patterns

### Client Usage
```kotlin
createStateQueryClient(websocketHost = "localhost", websocketPort = 1337).use { client ->
    val connected = client.connect()
    require(connected == true) { "Failed to connect" }
    val tip = client.chainTip()
}
```

### Protocol Messages
```kotlin
@Serializable
data class MsgQueryTipResponse(
    val result: QueryTipResult,
    val id: String,
    val jsonrpc: String = JSONRPC_VERSION,
)
```

---

## Before You Start

1. Read [`.agent/readme.md`](./.agent/readme.md) for full documentation index
2. Check [`.agent/system/architecture.md`](./.agent/system/architecture.md) for system overview
3. Review relevant workflow in [`.agent/workflows/`](./.agent/workflows/)

---

## Security Notes

- Never commit secrets or API keys
- Avoid checking in credentials for Ogmios nodes
