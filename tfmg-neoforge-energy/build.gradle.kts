plugins {
    kotlin("jvm")
}

group = "concerrox.blueberry.tfmgfe"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://api.modrinth.com/maven/") // Create: The Factory Must Grow
}

dependencies {
    implementation("maven.modrinth:create-tfmg:1.0.2f")
}

kotlin {
    jvmToolchain(21)
}