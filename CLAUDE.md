# Apache Sling Distribution API

## Overview

This bundle defines the public Java API for the Apache Sling Content Distribution (SCD) module. It contains no runnable logic — only interfaces, abstract types, and value objects that other bundles (distribution-core, distribution-journal, etc.) implement and consume. The central contract is `Distributor`, which accepts a `DistributionRequest` (type + paths) and returns a `DistributionResponse`. Extension points include `ImportPreProcessor`, `ImportPostProcessor`, and `InvalidationProcessor` for hooking into the import lifecycle, plus `DistributionTransportSecretProvider` for pluggable auth.

## Tech Stack

- Java 8, OSGi bundle (Apache Felix / bnd-maven-plugin)
- Dependencies: `org.apache.sling.api`, `javax.jcr`, `javax.servlet`, OSGi core/compendium, JetBrains annotations
- Build: Maven with `sling-bundle-parent` v47

## Build & Test Commands

```
mvn clean install            # build and run unit tests
mvn test -Dtest=DistributionResponseTest   # run a single test class
```

## Architecture

All types live under `org.apache.sling.distribution`:

| Package | Contents |
|---|---|
| (root) | `Distributor`, `DistributionRequest/Response`, `DistributionRequestType` (ADD/DELETE/PULL/…), `ImportPreProcessor`, `ImportPostProcessor`, `InvalidationProcessor` |
| `event` | `DistributionEventTopics`, `DistributionEventProperties` — OSGi event constants fired after distribute/import |
| `transport` | `DistributionTransportSecretProvider`, `DistributionTransportSecret` — auth plug-in SPI |

`DistributionRequestType` is an enum-like final class (not a Java enum) — don't try to switch on it as an enum.

## Conventions & Gotchas

- This is a **pure API bundle**: it deliberately exports all packages and has no `@Component` annotations. Do not add implementation code here.
- OSGi package versioning is managed via `package-info.java` files with `@Version` annotations. Bumping a type signature here requires a major/minor version bump in the corresponding `package-info.java`.
- The bundle is built with bnd but the POM inherits from `sling-bundle-parent` (Apache parent). `bnd-maven-plugin` derives the OSGi manifest automatically from the source; no manual `bnd.bnd` file is needed.
- Tests use JUnit 4 (not 5). Keep new tests in that style.
