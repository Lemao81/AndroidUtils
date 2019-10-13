apply(from = Paths.baseMaven)

plugins {
    java
    `java-library`
    id(PluginIds.kotlin)
    id(PluginIds.kotlinSerialization)
    id(PluginIds.archivaUpload)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

archiva {
    groupId = App.applicationId
    artifactId = project.name
    packaging = "jar"
}

dependencies {
    implementation(Libs.kotlinStd8)
    implementation(Libs.kotlinSerialization)
    implementation(Libs.kotlinCoroutine)
    implementation(Libs.koinCore)
    implementation(Libs.joda)
}