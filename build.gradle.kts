// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.20-1.0.25"
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
    id ("com.google.dagger.hilt.android") version "2.47" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.1.3")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.0")
        classpath ("com.google.gms:google-services:4.4.2")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.47")

    }
}