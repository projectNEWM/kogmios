# Claude Code Instructions

> **For Claude Code.** This file points to the centralized documentation in `.agent/`.

---

## Documentation Location

All agent documentation is centralized in the [`.agent/`](./.agent/) directory.

**Start here:** [`.agent/readme.md`](./.agent/readme.md)

---

## Quick Reference

### System Documentation
- [Architecture](./.agent/system/architecture.md) — System overview, modules, patterns

### Workflows
- [/build](./.agent/workflows/build.md) — Build project
- [/test](./.agent/workflows/test.md) — Run tests
- [/backend_dependencies](./.agent/workflows/backend_dependencies.md) — Dependency updates
- [/update-doc](./.agent/workflows/update-doc.md) — Update documentation

### Learning Resources
- [Task History](./.agent/task/) — Past implementation plans
- [SOPs](./.agent/SOPs/) — Standard operating procedures

### Skills

Skills are reusable instructions that an agent can load with the `skill` tool.

Current status:
- `git-commit-formatter` — Formats git commit messages to Conventional Commits when asked to commit or draft commit messages. See `.agent/skills/git-commit-formatter/SKILL.md`.

---

## Common Commands

### Build
```bash
./gradlew build                    # Build project
```

### Test
```bash
./gradlew test                     # Run all tests
```

### Lint
```bash
./gradlew ktlintCheck              # Check code style
./gradlew ktlintFormat             # Auto-fix formatting
```

---

## Project Structure

```
kogmios/
├── .agent/                 # 📚 Agent documentation (READ THIS)
├── buildSrc/               # Version catalog/constants
├── src/                    # Kotlin source/tests
└── gradle/                 # Gradle wrapper
```

---

## Creating Documentation

When completing features or resolving issues:

1. **Implementation plans** → Save to `.agent/task/{domain}/`
2. **Resolved issues** → Create SOP in `.agent/SOPs/{category}/`
3. **New workflows** → Add to `.agent/workflows/`

Run `/update-doc` workflow for guidance.
