import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("/home/daniel/.daniel-dev/keystore.jks")
            storePassword = "theStrokes23_ks"
            keyPassword = "theStrokes23_ps"
            keyAlias = "playstorekey"
        }
    }
    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"
    defaultConfig {
        applicationId = "daniel.perez.coviddata"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("release")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    lintOptions {
        isAbortOnError = false
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
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar" ))))
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.annotation:annotation:1.1.0")


    implementation(project(Project.core))
    implementation(project(Project.covidApi))
    implementation(project(Project.populationApi))
    implementation(project(Project.stateHistoricalData))
    implementation(project(Project.selectStateView))
    implementation(project(Project.testedVsUntested))

    implementation(Libs.anychart)

    implementation(Libs.conductor)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.loggingInterceptor)

    implementation(Libs.material)
    implementation(Libs.moshi)
    implementation(Libs.moshiCodegen)
    implementation(Libs.moshiKotlin)

    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)
    implementation(Libs.retrofitRxJava3Adapter)
    implementation(Libs.rxAndroid)
    implementation(Libs.rxJava3)

    implementation(Libs.timber)

}
