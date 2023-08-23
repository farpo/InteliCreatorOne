plugins {
    id("java")
    id("org.openjfx.javafxplugin") version "0.0.14"
}

group = "eu.ansquare"
version = "1.0-SNAPSHOT"

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
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
