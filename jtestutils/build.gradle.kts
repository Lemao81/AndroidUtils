apply(from = Paths.baseMaven)

plugins {
    java
    `java-library`
    id(PluginIds.kotlin)
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
    implementation(Libs.kotlinCoroutine)
    implementation(Libs.mockito)
    implementation(Libs.mockitoKotlin)
    implementation(Libs.jUnit4)
}