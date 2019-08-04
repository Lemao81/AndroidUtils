apply(from = Paths.baseMaven)

plugins {
    id(PluginIds.androidLibrary)
    kotlin(PluginIds.android)
    kotlin(PluginIds.kapt)
    id(PluginIds.archivaUpload)
}

android {
    configureAndroidExtension(this)
}

archiva {
    groupId = App.applicationId
    artifactId = project.name
}

dependencies {
    implementation(Libs.kotlinStd8)
    implementation(Libs.jutils)
    implementation(Libs.andutils)
    implementation(Libs.kotlinReflect)
    implementation(Libs.rxJava)
    implementation(Libs.firebaseCore)
    implementation(Libs.firebaseDatabase)
}
