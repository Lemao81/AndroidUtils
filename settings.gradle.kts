pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

include(":andutils", ":jutils", ":firebaseutils", ":andtestutils", ":resutils", ":customviewutils", ":jtestutils", ":rxutils")
