# 📚 Kogmios Agent Documentation Index

> **For AI Agents:** Read this file first to understand available documentation and when to reference each resource.

This folder contains documentation for assisting with development on the Kogmios project (Kotlin implementation of an Ogmios client). Use this index to quickly navigate to the relevant documentation for your current task.

---

## 🗺️ Quick Navigation

| Folder | Purpose | When to Read |
|--------|---------|--------------|
| [`/system`](./system/) | Architecture, build/publish, design patterns | **First**, for any architectural decisions or understanding system design |
| [`/task`](./task/) | PRDs and implementation plans history | When implementing new features similar to past work |
| [`/SOPs`](./SOPs/) | Standard Operating Procedures | When encountering known issues or following established patterns |
| [`/workflows`](./workflows/) | Step-by-step development workflows | When executing specific development tasks |

---

## 📁 Folder Details

### `/system` — Architecture & Patterns

**The source of truth for major architectural decisions.**

Read these files to understand:
- Overall system architecture and component relationships
- Client/protocol layout and data models
- Build, publish, and versioning expectations
- Integration patterns with Ogmios/Cardano

Files:
- `architecture.md` — System overview, modules, protocols, data flow

---

### `/task` — Implementation History

**Successful PRDs and implementation plans for reference.**

Before implementing a feature:
1. Check if a similar feature was implemented before
2. Use past plans as templates for consistency
3. Follow established patterns from successful implementations

This folder uses subdirectories per major feature/task.

---

### `/SOPs` — Standard Operating Procedures

**Learnings from resolved issues and best practices.**

When an issue is resolved or a complex integration succeeds:
1. Document the step-by-step solution
2. Include common pitfalls and how to avoid them
3. Reference related code or configuration

**To create a new SOP**, ask the agent:
> "Generate SOP for [task/integration name]"

---

### `/workflows` — Development Workflows

**Step-by-step guides for common development tasks.**

Available workflows:

| Workflow | Description | Trigger |
|----------|-------------|---------|
| [`build.md`](./workflows/build.md) | Build project | `/build` |
| [`test.md`](./workflows/test.md) | Run tests | `/test` |
| [`backend_dependencies.md`](./workflows/backend_dependencies.md) | Check dependency updates | `/backend_dependencies` |
| [`update-doc.md`](./workflows/update-doc.md) | Update documentation | `/update-doc` |

---

## 🏗️ Project Overview

**Kogmios** is a Kotlin client implementation for the Ogmios WebSocket interface. It wraps Ogmios JSON-WSP messages into typed models and uses coroutines for async operations.

### Technology Stack

| Layer | Technology | Notes |
|-------|------------|-------|
| **Language** | Kotlin 2.x on Java 21 | Kotlin JVM library |
| **Networking** | Ktor client (CIO + websockets) | WebSocket communication |
| **Serialization** | kotlinx-serialization | JSON-WSP payloads |
| **Concurrency** | Kotlin coroutines | Suspended client calls |
| **Logging** | Logback, Commons Logging | Client logging |
| **Build** | Gradle (Kotlin DSL) | Single module |

### Module Overview

```
kogmios
└── src/
    ├── main/kotlin/io/newm/kogmios/...
    └── test/kotlin/io/newm/kogmios/...
```

### Client Types

Kogmios exposes four logical clients:
- `StateQuery`
- `TxSubmit`
- `TxMonitor`
- `ChainSync`

---

## ⚡ Quick Commands

### Build & Test
```bash
# Build
./gradlew build

# Run tests
./gradlew test
```

### Code Quality
```bash
# Lint check
./gradlew ktlintCheck

# Auto-fix formatting
./gradlew ktlintFormat
```

---

## 🧩 Skills

**Skills are reusable instructions the agent can load with the `skill` tool.**

Current status:
- `git-commit-formatter` — Formats git commit messages to Conventional Commits when asked to commit or draft commit messages. See `.agent/skills/git-commit-formatter/SKILL.md`.

If skills are added, list them here with a short description and usage guidance.

## 🔐 Security Notes

- Never commit secrets or API keys
- Avoid checking in credentials for Ogmios nodes
