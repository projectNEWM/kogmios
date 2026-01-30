import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    java
    id("com.github.ben-manes.versions") version Versions.VERSIONS_PLUGIN
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT_PLUGIN
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.serialization") version Versions.KOTLIN
    id("signing")
    id("com.vanniktech.maven.publish") version Versions.MAVEN_PUBLISH
}

group = "io.newm"
version = "2.7.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "jitpack.io"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}")
    implementation("io.ktor:ktor-client-websockets:${Versions.KTOR}")

    implementation("io.ktor:ktor-client-cio-jvm:${Versions.KTOR}")

    implementation("commons-logging:commons-logging:${Versions.COMMONS_LOGGING}")
    implementation("ch.qos.logback:logback-classic:${Versions.LOGBACK}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION}")

    implementation("org.apache.commons:commons-numbers-fraction:${Versions.COMMONS_NUMBERS}")

    testImplementation("io.mockk:mockk:${Versions.MOCKK}")
    testImplementation("com.google.truth:truth:${Versions.GOOGLE_TRUTH}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.JUNIT}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}")
    testImplementation("org.junit.platform:junit-platform-launcher:${Versions.JUNIT_PLATFORM}")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}")
}

ktlint {
    version.set(Versions.KTLINT)
}

signing {
    useGpgCmd()
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates("io.newm", "kogmios", version.toString())
    pom {
        name.set("Kogmios")
        description.set("Kotlin Wrapper for Ogmios")
        url.set("https://github.com/projectNEWM/kogmios")
        licenses {
            license {
                name.set("Apache 2.0")
                url.set("https://github.com/projectNEWM/kogmios/blob/master/LICENSE")
                distribution.set("https://github.com/projectNEWM/kogmios/blob/master/LICENSE")
            }
        }
        developers {
            developer {
                id.set("AndrewWestberg")
                name.set("Andrew Westberg")
                email.set("andrewwestberg@gmail.com")
                organization.set("NEWM")
                organizationUrl.set("https://newm.io")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/projectNEWM/kogmios.git")
            developerConnection.set("scm:git:ssh://github.com/projectNEWM/kogmios.git")
            url.set("https://github.com/projectNEWM/kogmios")
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
    filterConfigurations = Spec<Configuration> {
//        println("checking ${it.name}")
        it.name.contains("ktlint", ignoreCase = true).not()
    }
}

project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain>().configureEach {
    val service = project.extensions.getByType<JavaToolchainService>()
    val customLauncher =
        service.launcherFor {
            this.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.majorVersion))
        }

    this.kotlinJavaToolchain.toolchain.use(customLauncher)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        freeCompilerArgs =
            listOf(
                "-Xjsr305=strict",
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlin.time.ExperimentalTime",
            )
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.withType<Test> {
    maxHeapSize = "8192m"
}
