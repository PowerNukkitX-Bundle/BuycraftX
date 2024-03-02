plugins {
    `java-library`
    idea
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.buycraft"

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    mavenLocal()
}

// Do not build this root project, this is only used as a control submodule
tasks.forEach {
    it.enabled = false
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = false
    }
}

// Enable gradle wrapper update task
tasks.wrapper {
    enabled = true
    gradleVersion = GradleVersion.current().version
}

tasks.prepareKotlinBuildScriptModel {
    enabled = true
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenLocal()
        maven {
            url = uri("https://repo.maven.apache.org/maven2/")
        }
        maven {
            url = uri("https://www.jitpack.io")
        }
        maven {
            url = uri("https://repo.opencollab.dev/maven-releases")
        }
        maven {
            url = uri("https://repo.opencollab.dev/maven-snapshots")
        }
    }

    dependencies {
        api("org.jetbrains:annotations:16.0.2")
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    tasks.withType<Javadoc> {
        options.encoding = "utf-8"
        val javadocOptions = options as CoreJavadocOptions
        javadocOptions.addStringOption(
            "source",
            java.sourceCompatibility.toString()
        )
        // Suppress some meaningless warnings
        javadocOptions.addStringOption("Xdoclint:none", "-quiet")
    }
}

