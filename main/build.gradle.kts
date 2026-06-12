plugins {
    alias(libs.plugins.modDevGradle)
    kotlin("jvm")
}

var modId = project.property("mod_id").toString()
group = project.property("mod_group_id").toString()
version = project.property("mod_version").toString()

neoForge {
    version = rootProject.property("neo_version").toString()
    parchment {
        mappingsVersion = rootProject.property("parchment_mappings_version").toString()
        minecraftVersion = rootProject.property("parchment_minecraft_version").toString()
    }
}

repositories {
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
    implementation(libs.kotlinForForge)
}