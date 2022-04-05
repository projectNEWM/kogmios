import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    java
    id("com.github.ben-manes.versions") version "0.42.0"
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.20"
}

group = "io.projectnewm"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

object Versions {
    const val commonsLogging = "1.2"
    const val coroutines = "1.6.1"
    const val googleTruth = "1.1.3"
    const val junit = "5.8.2"
    const val kotlin = "1.6.20"
    const val ktor = "2.0.0-beta-1"
    const val logback = "1.2.11"
    const val mockk = "1.12.3"
    const val kotlinxSerialization = "1.3.2"
}

repositories {
    mavenLocal()
    maven {
        name = "jitpack.io"
        url = uri("https://jitpack.io")
    }
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")
    implementation("io.ktor:ktor-client-websockets:${Versions.ktor}")

    implementation("io.ktor:ktor-client-cio-jvm:${Versions.ktor}")
    //implementation("io.ktor:ktor-client-java:${Versions.ktor}")
    //implementation("io.ktor:ktor-client-okhttp:${Versions.ktor}")

    implementation("commons-logging:commons-logging:${Versions.commonsLogging}")
    implementation("ch.qos.logback:logback-classic:${Versions.logback}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")

    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("com.google.truth:truth:${Versions.googleTruth}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
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
    kotlinJavaToolchain.jdk.use(
        "/usr/lib/jvm/java-16-openjdk-amd64",
        JavaVersion.VERSION_16
    )
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
        jvmTarget = "16"
    }
}

tasks.withType<Test> {
    maxHeapSize = "8192m"
}
