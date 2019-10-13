apply(from = Paths.baseMaven)

plugins {
    id(PluginIds.androidLibrary)
    kotlin(PluginIds.android)
    kotlin(PluginIds.kapt)
    id(PluginIds.kotlinSerialization)
    id(PluginIds.archivaUpload)
}

android {
    configureAndroidExtension()
}

archiva {
    groupId = App.applicationId
    artifactId = project.name
}

dependencies {
    implementation(Libs.kotlinStd8)
    implementation(Libs.kotlinReflect)
    implementation(Libs.kotlinSerialization)
    implementation(Libs.jutils)
    implementation(Libs.andutils)
    implementation(Libs.rxJava)
    implementation(Libs.firebaseCore)
    implementation(Libs.firebaseDatabase)
    implementation(Libs.firestore)
}
