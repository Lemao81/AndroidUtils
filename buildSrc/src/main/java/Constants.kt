object PluginIds {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val android = "android"
    const val kotlinKapt = "kotlin-kapt"
    const val kapt = "kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val gms = "com.google.gms.google-services"
    const val archivaUpload = "custom-archivaupload"
    const val maven = "maven"
    const val safeargs = "androidx.navigation.safeargs"
}

object Urls {
    const val mavenLocalSnapshot = "http://localhost:8080/repository/snapshots"
    const val mavenLocalInternal = "http://localhost:8080/repository/internal"
    const val fabric = "https://maven.fabric.io/public"
    const val jitpack = "https://jitpack.io"
    const val maven = "https://maven.google.com"
    const val autoDsl = "https://dl.bintray.com/juanchosaravia/autodsl"
}

object Const {
    const val androidTestRunner = "android.support.test.runner.AndroidJUnitRunner"
    const val androidxTestRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Extensions {
    const val android = "android"
}

object BuildTypes {
    const val release = "release"
    const val debug = "debug"
}

object Paths {
    const val baseProject = "../base/gradlefiles/base/base-project.gradle"
    const val baseAndroidLibrary = "../../base/gradlefiles/base/base-android-library.gradle"
    const val baseJavaLibrary = "../../base/gradlefiles/base/base-java-library.gradle"
    const val baseKotlinAndroidExtension = "../../base/gradlefiles/base/base-kotlin-android-extension.gradle"
    const val baseKapt = "../../base/gradlefiles/base/base-kapt.gradle"
    const val baseDatabinding = "../../base/gradlefiles/base/base-databinding.gradle"
    const val baseFlavors = "../../base/gradlefiles/base/base-flavors.gradle"
    const val baseArchivaUpload = "../../base/gradlefiles/base/base-archivaupload.gradle"
    const val ktlintGr = "../../base/gradlefiles/ktlint.gradle"
    const val ktlint = "../../base/gradlefiles/ktlint.gradle.kts"
}