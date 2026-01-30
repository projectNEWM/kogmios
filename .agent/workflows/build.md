---
description: Build Kogmios and run lint
---

# Build Workflow

Quick commands and procedures for building Kogmios.

## Quick Commands

### Full Build

// turbo
```bash
./gradlew build
```

### Clean Build

// turbo
```bash
./gradlew clean build
```

---

## Lint Check

// turbo
```bash
./gradlew ktlintCheck
```

// turbo
```bash
./gradlew ktlintFormat  # Auto-fix
```

---

## Troubleshooting

### Build Cache Issues

```bash
./gradlew clean build --refresh-dependencies
```

### Memory Issues

```bash
./gradlew build -Dorg.gradle.jvmargs="-Xmx4g"
```
