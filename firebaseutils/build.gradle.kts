apply(from = Paths.baseMaven)

plugins {
    id(PluginIds.androidLibrary)
    kotlin(PluginIds.android)
    kotlin(PluginIds.kapt)
    id(PluginIds.archivaUpload)
}

android {
    compileSdkVersion(Android.compileSdkVersion)
    defaultConfig {
        versionCode = App.versionCode
        versionName = App.versionName
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)

        multiDexEnabled = true
        testInstrumentationRunner = Const.androidTestRunner
    }

    dexOptions.preDexLibraries = true
    packagingOptions.pickFirst("protobuf.meta")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

archiva {
    groupId = App.applicationId
    artifactId = project.name
}

dependencies {
    implementation(Libs.jutils)
    implementation(Libs.andutils)
    implementation(Libs.kotlinStd8)
    implementation(Libs.kotlinReflect)
    implementation(Libs.rxJava)
    implementation(Libs.firebaseCore)
    implementation(Libs.firebaseDatabase)
}
