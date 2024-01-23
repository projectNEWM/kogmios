import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.hibernate.build.publish.auth.maven.MavenRepoAuthPlugin

plugins {
    java
    id("com.github.ben-manes.versions") version Versions.VERSIONS_PLUGIN
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT_PLUGIN
    kotlin("jvm") version Versions.KOTLIN
    kotlin("plugin.serialization") version Versions.KOTLIN
    id("maven-publish")
    id("signing")
    id("org.hibernate.build.maven-repo-auth") version Versions.MAVEN_REPO_AUTH_PLUGIN apply false
}

if (!project.hasProperty("isGithubActions")) {
    // only use this plugin if we're running locally, not on github.
    apply<MavenRepoAuthPlugin>()
}

group = "io.newm"
version = "2.0.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

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
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KOTLINX_DATETIME}")

    implementation("org.apache.commons:commons-numbers-fraction:${Versions.COMMONS_NUMBERS}")

    testImplementation("io.mockk:mockk:${Versions.MOCKK}")
    testImplementation("com.google.truth:truth:${Versions.GOOGLE_TRUTH}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.JUNIT}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}")
}

ktlint {
    version.set(Versions.KTLINT)
}

tasks {

    val sourcesJar by registering(Jar::class) {
        archiveClassifier.set("sources")
        dependsOn("classes")
        from(sourceSets["main"].allSource)
    }

    val javadocJar by registering(Jar::class) {
        archiveClassifier.set("javadoc")
        dependsOn("javadoc")
        from("${layout.buildDirectory}/javadoc")
    }

    artifacts {
        archives(javadocJar)
        archives(sourcesJar)
    }

    assemble {
        dependsOn("sourcesJar", "javadocJar")
    }
}

publishing {
    repositories {
        maven {
            name = "ossrh"
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            if (project.hasProperty("release")) {
                setUrl(releasesRepoUrl)
            } else {
                setUrl(snapshotsRepoUrl)
            }
        }
    }
    publications {
        create<MavenPublication>("mavenKotlin") {
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                groupId = "io.newm"
                artifactId = "kogmios"

                name.set("Kogmios")
                description.set("Kotlin Wrapper for Ogmios")
                url.set("https://github.com/projectNEWM/kogmios")
                licenses {
                    license {
                        name.set("Apache 2.0")
                        url.set("https://github.com/projectNEWM/kogmios/blob/master/LICENSE")
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
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenKotlin"])
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
    val customLauncher =
        service.launcherFor {
            this.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.majorVersion))
        }

    this.kotlinJavaToolchain.toolchain.use(customLauncher)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs =
            listOf(
                "-Xjsr305=strict",
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    maxHeapSize = "8192m"
}
