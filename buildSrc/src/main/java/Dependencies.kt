package buildSrc

object Version {
    // plugins
    const val customPlugins = "1.0.4"
    const val gmsPlugin = "4.1.0"
    const val androidGradlePlugin = "3.2.1"
    const val fabricPlugin = "1.24.4"
    const val hugoPlugin = "1.2.1"

    // utils
    const val andutils = "1.0.85"
    const val jutils = "1.0.22"
    const val firebaseutils = "1.0.6"
    const val resutils = "1.0.7"
    const val andtestutils = "1.0.10"
    const val customviewutils = "1.0.8"

    // custom views
    const val rangebar = "1.0.3"
    const val stackoverflowtag = "1.0.5"
    const val availableindicator = "1.0.0"

    // libs
    const val java7 = "1.7"
    const val java8 = "1.8"
    const val kotlin = "1.3.0"
    const val androidxCore = "1.0.0"
    const val androidxCoreKtx = "1.0.1"
    const val androidxAppcompat = "1.0.0"
    const val androidxAnnotation = "1.0.0"
    const val androidxConstraintLayout = "1.1.2"
    const val androidxLegacyV4 = "1.0.0"
    const val androidxRecyclerView = "1.0.0"
    const val androidxCardView = "1.0.0"
    const val androidxExifInterface = "1.0.0"
    const val androidxPreference = "1.0.0"
    const val androidxLifecycleExtensions = "2.0.0-rc01"
    const val androidxLifecycleLiveData = "2.0.0-rc01"
    const val androidxLifecycleLiveDataCore = "2.0.0-rc01"
    const val androidxLifecycleRuntime = "2.0.0-rc01"
    const val androidxLifecycleViewModel = "2.0.0-rc01"
    const val androidxRoomRuntime = "2.0.0-rc01"
    const val androidxRoomCompiler = "2.0.0-rc01"
    const val androidxTestRunner = "1.1.0"
    const val androidxTestRules = "1.1.0"
    const val androidxTestMonitor = "1.1.0"
    const val androidxTestUiAutomator = "2.2.0"
    const val androidxTestEspressoCore = "3.1.0"
    const val androidxTestEspressoIntents = "3.1.0"
    const val androidxTestExtJunit = "1.0.0"
    const val material = "1.0.0-rc01"
    const val kotlinCoroutine = "1.0.0"
    const val anko = "0.10.6"
    const val gmsBase = "16.0.1"
    const val gmsLocation = "16.0.0"
    const val firebaseCore = "16.0.4"
    const val firebaseCommon = "16.0.4"
    const val firebaseDatabase = "16.0.3"
    const val firebaseAuth = "16.0.4"
    const val firebaseCrash = "16.2.1"
    const val firebaseStorage = "16.0.3"
    const val firebaseUi = "4.2.1"
    const val support = "28.0.0"
    const val glide = "4.8.0"
    const val rxJava = "2.1.9"
    const val rxJavaAndroid = "2.0.1"
    const val rxBinding = "2.1.0"
    const val mapStruct = "1.2.0.Final"
    const val constraintLayout = "1.1.3"
    const val mosbyMvi = "3.1.0"
    const val dagger = "2.15"
    const val joda = "2.10.1"
    const val crystalRange = "1.1.3"
    const val imageCropper = "2.5.1"
    const val imageCompressor = "2.1.0"
    const val circleIndicator = "1.2.2@aar"
    const val circleImageView = "2.2.0"
    const val keyboardVisibility = "2.1.0"
    const val jUnit = "4.12"
    const val supportTest = "1.0.1"
    const val espresso = "3.0.2"
    const val crashLytics = "2.7.1@aar"
    const val apacheValidator = "1.6"
    const val javaxInject = "1"
    const val koin = "1.0.1"
    const val room = "1.0.0"
    const val mockito = "2.22.0"
    const val mockitoKotlin = "1.5.0"
    const val powermock = "1.6.5"
    const val mockk = "1.8.12.kotlin13"
    const val frodo = "0.8.3"
    const val retrofit2 = "2.4.0"
    const val retrofit2CoroutineAdapter = "1.0.0"
    const val buildTimeTracker = "0.11.0"
    const val archLifecycle = "1.1.1"
    const val okLog = "2.3.0"
    const val archNavigation = "1.0.0-alpha07"
    const val leakCanary = "1.6.2"
    const val rxDisposer = "1.0.0-alpha.1"
    const val transitionX = "1.0.0-beta4"
    const val khttp = "0.1.0"
    const val kakao = "1.4.0"
    const val uiAutomator = "2.1.1"
    const val multidex = "1.0.3"
    const val permissions = "3.7"
    const val ktlint = "0.29.0"
    const val androidxTestCore = "1.0.0"
    const val autoDsl = "0.0.9"
    const val log4k = "1.0.1"
    const val assertJ = "3.11.1"
}

object Lib {
    // selfmade
    const val andutils = "com.jueggs:andutils:${Version.andutils}"
    const val jutils = "com.jueggs:jutils:${Version.jutils}"
    const val firebaseutils = "com.jueggs:firebaseutils:${Version.firebaseutils}"
    const val resutils = "com.jueggs:resutils:${Version.resutils}"
    const val andtestutils = "com.jueggs:andtestutils:${Version.andtestutils}"
    const val customviewutils = "com.jueggs:customviewutils:${Version.customviewutils}"
    const val rangebar = "com.jueggs.customview:rangebar:${Version.rangebar}"
    const val stackoverflowtag = "com.jueggs.customview:stackoverflowtag:${Version.stackoverflowtag}"
    const val availableindicator = "com.jueggs.customview:availableindicator:${Version.availableindicator}"

    // libs
    const val kotlinStd7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val kotlinStd8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Version.kotlin}"
    const val kotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutine}"
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutine}"
    const val androidxCore = "androidx.core:core:${Version.androidxCore}"
    const val androidxCoreKtx = "androidx.core:core-ktx:${Version.androidxCoreKtx}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Version.androidxAppcompat}"
    const val androidxAnnotation = "androidx.annotation:annotation:${Version.androidxAnnotation}"
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Version.androidxConstraintLayout}"
    const val androidxLegacyV4 = "androidx.legacy:legacy-support-v4:${Version.androidxLegacyV4}"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Version.androidxRecyclerView}"
    const val androidxCardView = "androidx.cardview:cardview:${Version.androidxCardView}"
    const val androidxExifInterface = "androidx.exifinterface:exifinterface:${Version.androidxExifInterface}"
    const val androidxPreference = "androidx.preference:preference:${Version.androidxPreference}"
    const val androidxLifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.androidxLifecycleExtensions}"
    const val androidxLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata:${Version.androidxLifecycleLiveData}"
    const val androidxLifecycleLiveDataCore = "androidx.lifecycle:lifecycle-livedata-core:${Version.androidxLifecycleLiveDataCore}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Version.androidxLifecycleRuntime}"
    const val androidxLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Version.androidxLifecycleViewModel}"
    const val androidxRoomRuntime = "androidx.room:room-runtime:${Version.androidxRoomRuntime}"
    const val androidxRoomCompiler = "androidx.room:room-compiler:${Version.androidxRoomCompiler}"
    const val androidxTestRunner = "androidx.test:runner:${Version.androidxTestRunner}"
    const val androidxTestRules = "androidx.test:rules:${Version.androidxTestRules}"
    const val androidxTestMonitor = "androidx.test:monitor:${Version.androidxTestMonitor}"
    const val androidxTestUiAutomator = "androidx.test.uiautomator:uiautomator:${Version.androidxTestUiAutomator}"
    const val androidxTestEspressoCore = "androidx.test.espresso:espresso-core:${Version.androidxTestEspressoCore}"
    const val androidxTestEspressoIntents = "androidx.test.espresso:espresso-intents:${Version.androidxTestEspressoIntents}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${Version.androidxTestExtJunit}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val supportV4 = "com.android.support:support-v4:${Version.support}"
    const val supportAppcompat = "com.android.support:appcompat-v7:${Version.support}"
    const val supportPreferenceV7 = "com.android.support:preference-v7:${Version.support}"
    const val supportDesign = "com.android.support:design:${Version.support}"
    const val supportRecyclerView = "com.android.support:recyclerview-v7:${Version.support}"
    const val supportCardView = "com.android.support:cardview-v7:${Version.support}"
    const val supportAnnotations = "com.android.support:support-annotations:${Version.support}"
    const val supportExifinterface = "com.android.support:exifinterface:${Version.support}"
    const val supportGridLayout = "com.android.support:gridlayout-v7:${Version.support}"
    const val supportPalette = "com.android.support:palette-v7:${Version.support}"
    const val supportMultidex = "com.android.support:multidex:${Version.multidex}"
    const val supportConstraintLayout = "com.android.support.constraint:constraint-layout:${Version.constraintLayout}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
    const val rxJavaAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxJavaAndroid}"
    const val rxBinding = "com.jakewharton.rxbinding2:rxbinding-kotlin:${Version.rxBinding}"
    const val rxBindingRecyclerView = "com.jakewharton.rxbinding2:rxbinding-recyclerview-v7-kotlin:${Version.rxBinding}"
    const val rxDisposer = "io.sellmair:disposer:${Version.rxDisposer}"
    const val databindingBase = "com.android.databinding:baseLibrary:${Version.androidGradlePlugin}"
    const val databindingCompiler = "com.android.databinding:compiler:${Version.androidGradlePlugin}"
    const val firebaseCore = "com.google.firebase:firebase-core:${Version.firebaseCore}"
    const val firebaseCommon = "com.google.firebase:firebase-common:${Version.firebaseCommon}"
    const val firebaseDatabase = "com.google.firebase:firebase-database:${Version.firebaseDatabase}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Version.firebaseAuth}"
    const val firebaseCrash = "com.google.firebase:firebase-crash:${Version.firebaseCrash}"
    const val firebaseStorage = "com.google.firebase:firebase-storage:${Version.firebaseStorage}"
    const val firebaseUiDatabase = "com.firebaseui:firebase-ui-database:${Version.firebaseUi}"
    const val firebaseUiAuth = "com.firebaseui:firebase-ui-auth:${Version.firebaseUi}"
    const val gmsBase = "com.google.android.gms:play-services-base:${Version.gmsBase}"
    const val gmsLocation = "com.google.android.gms:play-services-location:${Version.gmsLocation}"
    const val mosbyMvi = "com.hannesdorfmann.mosby3:mvi:${Version.mosbyMvi}"
    const val anko = "org.jetbrains.anko:anko:${Version.anko}"
    const val ankoDesign = "org.jetbrains.anko:anko-design:${Version.anko}"
    const val ankoCommons = "org.jetbrains.anko:anko-commons:${Version.anko}"
    const val dagger = "com.google.dagger:dagger-android:${Version.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Version.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    const val joda = "joda-time:joda-time:${Version.joda}"
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    const val crystalRange = "com.crystal:crystalrangeseekbar:${Version.crystalRange}"
    const val imageCropper = "com.theartofdev.edmodo:android-image-cropper:${Version.imageCropper}"
    const val imageCompressor = "id.zelory:compressor:${Version.imageCompressor}"
    const val circleIndicator = "me.relex:circleindicator:${Version.circleIndicator}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Version.circleImageView}"
    const val keyboardVisibility = "net.yslibrary.keyboardvisibilityevent:keyboardvisibilityevent:${Version.keyboardVisibility}"
    const val mapStruct = "org.mapstruct:mapstruct-jdk8:${Version.mapStruct}"
    const val mapStructProcessor = "org.mapstruct:mapstruct-processor:${Version.mapStruct}"
    const val crashLytics = "com.crashlytics.sdk.android:crashlytics:${Version.crashLytics}"
    const val apacheValidator = "commons-validator:commons-validator:${Version.apacheValidator}"
    const val javaxInject = "javax.inject:javax.inject:${Version.javaxInject}"
    const val koinAndroid = "org.koin:koin-android:${Version.koin}"
    const val koinAndroidViewModel = "org.koin:koin-android-viewmodel:${Version.koin}"
    const val koinScope = "org.koin:koin-android-scope:${Version.koin}"
    const val koinTest = "org.koin:koin-test:${Version.koin}"
    const val roomRuntime = "android.arch.persistence.room:runtime:${Version.room}"
    const val roomCompiler = "android.arch.persistence.room:compiler:${Version.room}"
    const val roomRxJava = "android.arch.persistence.room:rxjava2:${Version.room}"
    const val archLifecycleExtensions = "android.arch.lifecycle:extensions:${Version.archLifecycle}"
    const val archLifecycleCompiler = "android.arch.lifecycle:compiler:${Version.archLifecycle}"
    const val archLifecycleRuntime = "android.arch.lifecycle:runtime:${Version.archLifecycle}"
    const val archLifecycleViewModel = "android.arch.lifecycle:viewmodel:${Version.archLifecycle}"
    const val archLifeCycleLiveData = "android.arch.lifecycle:livedata:${Version.archLifecycle}"
    const val archLifeCycleLiveDataCore = "android.arch.lifecycle:livedata-core:${Version.archLifecycle}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Version.retrofit2}"
    const val retrofit2GsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit2}"
    const val retrofit2CoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:${Version.retrofit2CoroutineAdapter}"
    const val okLog = "com.github.simonpercic:oklog3:${Version.okLog}"
    const val archNavigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Version.archNavigation}"
    const val archNavigationUi = "android.arch.navigation:navigation-ui-ktx:${Version.archNavigation}"
    const val archNavigationSafeArgs = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Version.archNavigation}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"
    const val leakCanaryNoop = "com.squareup.leakcanary:leakcanary-android-no-op:${Version.leakCanary}"
    const val leakCanarySupport = "com.squareup.leakcanary:leakcanary-support-fragment:${Version.leakCanary}"
    const val transitionX = "in.arunkumarsampath:transition-x:${Version.transitionX}"
    const val khttp = "khttp:khttp:${Version.khttp}"
    const val permissions = "com.nabinbhandari.android:permissions:${Version.permissions}"
    const val ktlint = "com.github.shyiko:ktlint:${Version.ktlint}"
    const val autoDsl = "com.juanchosaravia.autodsl:annotation:${Version.autoDsl}"
    const val autoDslProcessor = "com.juanchosaravia.autodsl:processor:${Version.autoDsl}"
    const val log4kAndroid = "com.log4k:log4k-android:${Version.log4k}"

    // testing
    const val jUnit = "junit:junit:${Version.jUnit}"
    const val testRunner = "com.android.support.test:runner:${Version.supportTest}"
    const val testRules = "com.android.support.test:rules:${Version.supportTest}"
    const val espressoCore = "com.android.support.test.espresso:espresso-core:${Version.espresso}"
    const val espressoIntents = "com.android.support.test.espresso:espresso-intents:${Version.espresso}"
    const val uiAutomator = "com.android.support.test.uiautomator:uiautomator-v18:${Version.uiAutomator}"
    const val mockito = "org.mockito:mockito-core:${Version.mockito}"
    const val powermock = "org.powermock:powermock:${Version.powermock}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Version.mockitoKotlin}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Version.mockk}"
    const val kakao = "com.agoda.kakao:kakao:${Version.kakao}"
    const val androidxTestCore = "androidx.test:core:${Version.androidxTestCore}"
    const val assertJ = "org.assertj:assertj-core:${Version.assertJ}"
}

object Plugin {
    const val custom = "com.jueggs:gradleplugins:${Version.customPlugins}"
    const val androidGradle = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val gms = "com.google.gms:google-services:${Version.gmsPlugin}"
    const val fabric = "io.fabric.tools:gradle:${Version.fabricPlugin}"
    const val hugo = "com.jakewharton.hugo:hugo-plugin:${Version.hugoPlugin}"
    const val frodo = "com.fernandocejas.frodo:frodo-plugin:${Version.frodo}"
    const val buildTimeTracker = "net.rdrei.android.buildtimetracker:gradle-plugin:${Version.buildTimeTracker}"
    const val safeargs = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Version.archNavigation}"
}

object PluginId {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val gms = "com.google.gms.google-services"
    const val archivaUpload = "custom-archivaupload"
    const val maven = "maven"
    const val safeargs = "androidx.navigation.safeargs"
}

object Url {
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

object Extension {
    const val android = "android"
}

object BuildType {
    const val release = "release"
    const val debug = "debug"
}

object Path {
    const val baseProject = "../base/gradlefiles/base/base-project.gradle"
    const val baseAndroidLibrary = "../../base/gradlefiles/base/base-android-library.gradle"
    const val baseJavaLibrary = "../../base/gradlefiles/base/base-java-library.gradle"
    const val baseKotlinAndroidExtension = "../../base/gradlefiles/base/base-kotlin-android-extension.gradle"
    const val baseKapt = "../../base/gradlefiles/base/base-kapt.gradle"
    const val baseDatabinding = "../../base/gradlefiles/base/base-databinding.gradle"
    const val baseFlavors = "../../base/gradlefiles/base/base-flavors.gradle"
    const val baseArchivaUpload = "../../base/gradlefiles/base/base-archivaupload.gradle"
    const val ktlint = "../../base/gradlefiles/ktlint.gradle"
}