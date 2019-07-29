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
    implementation(Libs.andutils)
    implementation(Libs.kakao)
    implementation(Libs.androidxTestRules)
    implementation(Libs.androidxTestUiAutomator)
    implementation(Libs.androidxTestEspressoCore)
}
