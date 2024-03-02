dependencies {
    implementation(project(":shared"))
    compileOnly(libs.com.github.powernukkitx.powernukkitx)
}

val version: String by rootProject
tasks.processResources {
    expand(mapOf("version" to version))
}