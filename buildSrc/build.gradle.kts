plugins {
    java
    kotlin("jvm") version "2.3.21"
    `java-gradle-plugin`
}

repositories {
    maven("https://maven.fabricmc.net/")
    maven ("https://maven.glass-launcher.net/babric")
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        create("glassplugins") {
            id = "resourceGen"
            implementationClass = "net.glasslauncher.gradleplugin.resourcegen.ResourceGenPlugin"
        }
    }
}