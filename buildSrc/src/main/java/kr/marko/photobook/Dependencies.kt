package kr.marko.photobook

object Versions {
    const val androidx_appcompat = "1.6.1"
    const val androidx_splashScreen = "1.0.0-beta02"
    const val androidx_nav = "2.5.3"

    const val kotlin_reflect = "1.8.21"

    const val hilt = "2.46"
    const val orbit = "4.6.1"

    //for Test
    const val kotest = "5.6.2"
    const val mockk = "1.13.5"
    const val mockito_kotlin = "4.1.0"
}

object Libraries {
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_splashScreen = "androidx.core:core-splashscreen:${Versions.androidx_splashScreen}" //하위버전 호환성을 위한 조치
    const val androidx_activity = "androidx.activity:activity-ktx:${Versions.androidx_appcompat}"
    const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_nav}"
    const val nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_nav}"

    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_reflect}"

    const val viewbinding = "com.github.yogacp:android-viewbinding:1.0.4"

    const val hilt_core = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    const val orbit_core = "org.orbit-mvi:orbit-viewmodel:${Versions.orbit}"
    const val orbit_test = "org.orbit-mvi:orbit-test:${Versions.orbit}"

    const val rxKotin = "io.reactivex.rxjava3:rxkotlin:3.0.1"

    const val javaxInject = "javax.inject:javax.inject:1"

    const val coroutineWithRx = "org.jetbrains.kotlinx:kotlinx-coroutines-rx3:1.6.4"

    //for Test
    const val kotest_jvm = "io.kotest:kotest-runner-junit5:${Versions.kotest}"
    const val kotest_assertion = "io.kotest:kotest-assertions-core:${Versions.kotest}"
    const val mockk_jvm = "io.mockk:mockk:${Versions.mockk}"
    const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_kotlin}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_kotlin}"
    const val fixture = "com.appmattus.fixture:fixture:1.2.0"
}