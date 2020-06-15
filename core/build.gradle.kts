
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation( fileTree(mapOf("dir" to "libs", "include" to "*.jar" ) ) )
    implementation( "org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}" )
    implementation( "androidx.core:core-ktx:1.2.0" )
    implementation( "androidx.appcompat:appcompat:1.1.0" )
    testImplementation( "junit:junit:4.12" )
    androidTestImplementation( "androidx.test.ext:junit:1.1.1" )
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.2.0" )


    implementation( Libs.conductor )
    implementation( Libs.dagger )
    kapt( Libs.daggerCompiler )
    implementation( Libs.material )
    implementation( Libs.rxJava3 )
    implementation( Libs.timber )


}