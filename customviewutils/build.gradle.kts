apply(from = Paths.baseMaven)

plugins {
    id(PluginIds.androidLibrary)
    kotlin(PluginIds.android)
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

    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
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
    implementation(Libs.androidxAppcompat)
    implementation(Libs.androidxCoreKtx)
    implementation(Libs.log4k)
    implementation(Libs.log4kAndroid)
}
