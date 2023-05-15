import kr.marko.photobook.Configuration
import kr.marko.photobook.Libraries

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "kr.marko.photobook"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "kr.marko.photobook"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Libraries.kotlin_reflect)

    //androidx
    implementation("androidx.core:core-ktx:1.7.0")
    implementation(Libraries.androidx_appcompat)
    implementation("com.google.android.material:material:1.8.0")
    implementation(Libraries.androidx_splashScreen)
    implementation(Libraries.androidx_activity)
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
    //timber
    implementation(Libraries.timber)

    testImplementation(Libraries.kotest_jvm)
    testImplementation(Libraries.kotest_assertion)
    testImplementation(Libraries.mockito_kotlin)
    testImplementation(Libraries.mockito_inline)
    testImplementation(Libraries.fixture)
    testImplementation(Libraries.coroutineWithRx)
    testRuntimeOnly(Libraries.junit5_jupiter_engine)

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}