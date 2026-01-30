---
description: Check and research dependency updates with user approval workflow
---

# Dependencies Workflow

Workflow for checking and updating Kogmios dependencies safely.

## Overview

Uses Gradle's dependency updates plugin to check for available updates, research compatibility, and apply updates with user approval.

---

## Quick Check

### List Available Updates

// turbo
```bash
./gradlew dependencyUpdates -Drevision=release --no-parallel --no-configuration-cache
```

This outputs a report showing:
- Current version
- Available updates
- Stability level

> [!NOTE]
> The `--no-parallel` and `--no-configuration-cache` flags are required because the dependency updates plugin doesn't support Gradle's configuration cache or parallel execution.

---

## Update Process

### 1. Generate Report

```bash
./gradlew dependencyUpdates -Drevision=release --no-parallel --no-configuration-cache
```

### 2. Research Each Update

For each major update:
1. Check release notes
2. Look for breaking changes
3. Check compatibility with Kotlin/JVM version

### 3. Update in buildSrc

Dependencies are defined in:
```
buildSrc/src/main/kotlin/Versions.kt
```

### 4. Test

// turbo
```bash
./gradlew clean build test
```

---

## Key Dependencies

| Dependency | Location | Notes |
|------------|----------|-------|
| Kotlin | `Versions.KOTLIN` | Core language |
| Ktor | `Versions.KTOR` | WebSocket client |
| Coroutines | `Versions.COROUTINES` | Async |
| Serialization | `Versions.KOTLINX_SERIALIZATION` | JSON serialization |
| Ktlint | `Versions.KTLINT` | Code style |

---

## Safety Guidelines

1. **Test thoroughly** after updates
2. **Update one dependency at a time** for major versions
3. **Check breaking changes** in release notes
4. **Run integration tests** after updating
