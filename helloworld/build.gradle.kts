import Versions.compose

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.compose") version Versions.compose
    application
}

group = "me.ssi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("file://${rootDir}/.m2repo/")
}

dependencies {
    implementation(Versions.library)
    implementation(compose.desktop.currentOs)
    implementation(project(":juicefactory"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(Versions.jvmLevel)
}

application {
    mainClass.set("MainKt")
}