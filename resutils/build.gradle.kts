apply(from = Paths.baseMaven)

plugins {
    id(PluginIds.androidLibrary)
    kotlin(PluginIds.android)
    kotlin(PluginIds.kapt)
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
    implementation(Libs.andutils)
    implementation(Libs.androidxRecyclerView)
    implementation(Libs.androidxAppcompat)
    implementation(Libs.androidxConstraintLayout)
}
