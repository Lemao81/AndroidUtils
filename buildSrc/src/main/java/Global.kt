import org.gradle.api.Project
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion

fun disableLintTasks(project: Project) {
    project.tasks.whenTaskAdded {
        if (this.name.startsWith("lint")) {
            this.enabled = false
        }
    }
}

fun configureAndroidExtension(android: BaseExtension) {
    android.compileSdkVersion(Android.compileSdkVersion)
    android.defaultConfig {
        versionCode = App.versionCode
        versionName = App.versionName
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)

        multiDexEnabled = true
        testInstrumentationRunner = Const.androidTestRunner
    }

    android.dexOptions.preDexLibraries = true
    android.packagingOptions.pickFirst("protobuf.meta")

    android.compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}