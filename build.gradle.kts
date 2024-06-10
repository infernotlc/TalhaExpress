// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath (libs.dagger.hilt.android.gradle.plugin)
        classpath (libs.google.services)
        classpath (libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.22"
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}