plugins {
    id("java")
    id("org.openjfx.javafxplugin") version "0.0.14"
    application
}

group = "eu.ansquare"
version = "0.1.1-SNAPSHOT"

repositories {
    mavenCentral()
}
javafx {
    modules("javafx.controls", "javafx.fxml")
}
dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.3.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.3.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "processResources")) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}

application {
    mainClass.set("eu.ansquare.intellicreator.one.Main")
}