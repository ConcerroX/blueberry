plugins {
    alias(libs.plugins.modDevGradle)
    kotlin("jvm")
}

val parentProject = project.parent!!
val modId = parentProject.property("mod_id").toString()
group = parentProject.property("mod_group_id").toString()
version = parentProject.property("mod_version").toString()

evaluationDependsOn(parentProject.path)

neoForge {
    version = rootProject.property("neo_version").toString()
    parchment {
        mappingsVersion = rootProject.property("parchment_mappings_version").toString()
        minecraftVersion = rootProject.property("parchment_minecraft_version").toString()
    }
    runs {
        register("data") {
            data()
            programArguments.addAll(
                "--mod", modId,
                "--all",
                "--output", parentProject.file("src/generated/resources/").absolutePath,
                "--existing", parentProject.file("src/main/resources/").absolutePath,
            )
        }
    }
    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
            sourceSet(parentProject.sourceSets.main.get())
        }
    }
}

repositories.addAll(parentProject.repositories)
dependencies.implementation(parentProject)

tasks.withType<ProcessResources> {
    val replaceProperties = mapOf(
        "mod_id" to modId,
        "mod_version" to version,
        "mod_name" to parentProject.property("mod_name").toString(),
        "mod_authors" to parentProject.property("mod_authors").toString(),
        "mod_description" to parentProject.property("mod_description").toString(),

        "mod_license" to rootProject.property("mod_license").toString(),
        "neo_version_range" to rootProject.property("neo_version_range").toString(),
        "loader_version_range" to rootProject.property("loader_version_range").toString(),
        "minecraft_version_range" to rootProject.property("minecraft_version_range").toString(),
    )
    inputs.properties(replaceProperties)
    filesMatching("META-INF/neoforge.mods.toml") { expand(replaceProperties) }
}