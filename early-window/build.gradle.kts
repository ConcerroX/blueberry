plugins {
    alias(libs.plugins.modDevGradle)
}

var modId = "blueberry_early_window"
group = "concerrox.blueberry.earlywindow"
version = "1.0.0"

neoForge {
    version = rootProject.property("neo_version").toString()
    parchment {
        mappingsVersion = rootProject.property("parchment_mappings_version").toString()
        minecraftVersion = rootProject.property("parchment_minecraft_version").toString()
    }
}