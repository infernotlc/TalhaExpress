plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-parcelize")
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
    id ("com.google.dagger.hilt.android")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.tlh.talhaexpress"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tlh.talhaexpress"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



    //Navigation component
    val nav_version = "2.5.2"
    implementation (libs.androidx.navigation.fragment.ktx.v252)
    implementation (libs.androidx.navigation.ui.ktx)

    //loading button
    implementation (libs.loading.button.android)

    //Glide
    implementation (libs.glide)

    //circular image
    implementation (libs.circleimageview)

    //viewpager2 indicatior
    implementation (libs.viewpagerindicator)

    //stepView
    implementation (libs.stepview)

    //Android Ktx
    implementation (libs.navigation.fragment.ktx)

    //Dagger hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)


    //Firebase
    implementation (libs.firebase.auth)

    //Coroutines with firebase
    implementation (libs.kotlinx.coroutines.play.services)

}

kapt {
    correctErrorTypes = true
}
