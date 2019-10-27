buildscript {
    repositories {
        google()
        jcenter()
        maven(Urls.mavenLocalInternal)
    }

    dependencies {
        classpath(Plugins.androidBuild)
        classpath(Plugins.kotlin)
        classpath(Plugins.gms)
        classpath(Plugins.kotlinSerialization)
        classpath(Plugins.custom)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(Urls.mavenLocalInternal)
    }

    configureKotlinCompileTasks(this)
    disableLintTasks(this)
}

subprojects {
    apply(from = Paths.ktlint)
}