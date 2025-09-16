pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven ("https://maven.glass-launcher.net/babric")
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":Glass-GUIs")
project(":Glass-GUIs").projectDir = file("../Glass-GUIs")
