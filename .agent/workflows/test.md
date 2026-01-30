---
description: Run Kogmios test suite
---

# Test Workflow

Quick commands for running tests in Kogmios.

## Quick Commands

### Run All Tests

// turbo

```bash
./gradlew test
```

---

## Test with Logging

```bash
./gradlew test --info
```

---

## Run Single Test Class

```bash
./gradlew test --tests "io.newm.kogmios.protocols.statequery.StateQueryTest"
```

---

## Troubleshooting

### Flaky Tests

Force re-run:

```bash
./gradlew test --rerun-tasks
```
