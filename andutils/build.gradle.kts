import kotlin.collections.mapOf
import kotlin.collections.listOf

apply(from = Paths.baseMaven)

plugins {
    id(PluginIds.androidLibrary)
    kotlin(PluginIds.android)
    kotlin(PluginIds.kapt)
    id(PluginIds.archivaUpload)
}

android {
    configureAndroidExtension(this)
    dataBinding.isEnabled = true
}

archiva {
    groupId = App.applicationId
    artifactId = project.name
}

kapt {
    useBuildCache = true
}

dependencies {
    compileOnly(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))

    implementation(Libs.kotlinStd8)
    implementation(Libs.kotlinReflect)
    implementation(Libs.jutils)
    implementation(Libs.androidxAppcompat)
    implementation(Libs.androidxCore)
    implementation(Libs.androidxCoreKtx)
    implementation(Libs.androidxRecyclerView)
    implementation(Libs.androidxConstraintLayout)
    implementation(Libs.androidxLifecycleExtensions)
    implementation(Libs.androidxLifecycleLiveData)
    implementation(Libs.androidxLifecycleLiveDataCore)
    implementation(Libs.androidxRoomRuntime)
    implementation(Libs.androidxNavigationUi)
    implementation(Libs.material)
    implementation(Libs.gmsBase)
    implementation(Libs.anko)
    implementation(Libs.joda)
    implementation(Libs.glide)
    implementation(Libs.koinAndroid)
    implementation(Libs.kotlinCoroutineAndroid)
    implementation(Libs.permissions)
    implementation(Libs.log4k)
    implementation(Libs.log4kAndroid)
    implementation(Libs.apacheValidator)

    kapt(Libs.glideCompiler)

    testImplementation(Libs.jUnit4)
}