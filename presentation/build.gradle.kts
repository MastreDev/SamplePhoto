import kr.marko.photobook.Configuration
import kr.marko.photobook.Libraries

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "kr.marko.photobook.presentation"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(project(":domain"))

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")

    //navigation
    implementation(Libraries.nav_fragment)
    implementation(Libraries.nav_ui)
    //viewbinding util
    implementation(Libraries.viewbinding)
    //hilt
    implementation(Libraries.hilt_core)
    kapt(Libraries.hilt_kapt)
    //orbit
    implementation(Libraries.orbit_core)
    //Coroutine with Rx
    implementation(Libraries.coroutineWithRx)
    //timber
    implementation(Libraries.timber)

    testImplementation(Libraries.kotest_jvm)
    testImplementation(Libraries.kotest_assertion)
    testImplementation(Libraries.mockito_kotlin)
    testImplementation(Libraries.mockito_inline)
    testImplementation(Libraries.fixture)
    testImplementation(Libraries.orbit_test)
    testRuntimeOnly(Libraries.junit5_jupiter_engine)

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}