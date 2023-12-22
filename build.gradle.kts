import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("com.github.gmazzo.buildconfig") version "3.1.0"
    application
}

subprojects {
    apply(plugin = "com.github.gmazzo.buildconfig")
}

val labNumber = 4

allprojects {
    buildConfig {
        buildConfigField("int", "LAB_NUMBER", "${labNumber}")
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("file://${rootDir}/.m2repo/")
    google()
}

dependencies {
    if (labNumber > 1) {
        implementation(project(":helloworld"))
    }
    implementation(project(":caffe"))
    implementation(project(":juicefactory"))
    implementation(Versions.library)
    testImplementation(kotlin("test"))
}

sourceSets {
    if (labNumber < 2) {
        named("main") {
            java.srcDir("./helloworld/src/main/kotlin/")
        }
    }

    test {
        kotlin {
            srcDir(
                when (labNumber) {
                    1 -> "src/lab1/"
                    2 -> "src/lab2/"
                    3 -> "src/lab3/"
                    4 -> "src/lab4/"
                    5 -> "src/lab5/"
                    else -> throw IllegalStateException("Wrong Lab number $labNumber")
                }
            )
        }
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath

    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

tasks.withType<Test> {
    testLogging {
        showCauses = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        events.add(org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT)
        events.add(org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR)
        events.add(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED)
        events.add(org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
        events.add(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
    }
}

kotlin {
    jvmToolchain(Versions.jvmLevel)
}