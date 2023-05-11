import kr.marko.photobook.Libraries

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    //rx
    implementation(Libraries.rxKotin)

    //di
    implementation(Libraries.javaxInject)

    testImplementation(Libraries.kotest_jvm)
    testImplementation(Libraries.kotest_assertion)
    testImplementation(Libraries.mockito_kotlin)
    testImplementation(Libraries.mockito_inline)
    testImplementation(Libraries.fixture)
    testImplementation(Libraries.coroutineWithRx)
}