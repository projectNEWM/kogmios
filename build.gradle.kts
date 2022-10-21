import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    java
    id("com.github.ben-manes.versions") version "0.43.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("maven-publish")
}

group = "io.newm"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

object Versions {
    const val commonsLogging = "1.2"
    const val coroutines = "1.6.4"
    const val googleTruth = "1.1.3"
    const val junit = "5.9.1"
    const val kotlin = "1.7.20"
    const val ktor = "2.1.2"
    const val logback = "1.4.4"
    const val mockk = "1.13.2"
    const val kotlinxDatetime = "0.4.0"
    const val kotlinxSerialization = "1.4.1"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "jitpack.io"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")
    implementation("io.ktor:ktor-client-websockets:${Versions.ktor}")

    implementation("io.ktor:ktor-client-cio-jvm:${Versions.ktor}")

    implementation("commons-logging:commons-logging:${Versions.commonsLogging}")
    implementation("ch.qos.logback:logback-classic:${Versions.logback}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}")

    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("com.google.truth:truth:${Versions.googleTruth}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<DependencyUpdatesTask> {
    // Example 1: reject all non stable versions
    rejectVersionIf {
        isNonStable(candidate.version)
    }

    // Example 2: disallow release candidates as upgradable versions from stable versions
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }

    // Example 3: using the full syntax
    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                    reject("Release candidate")
                }
            }
        }
    }
}

project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain>().configureEach {
    val service = project.extensions.getByType<JavaToolchainService>()
    val customLauncher = service.launcherFor {
        this.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.majorVersion))
    }

    this.kotlinJavaToolchain.toolchain.use(customLauncher)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    maxHeapSize = "8192m"
}
