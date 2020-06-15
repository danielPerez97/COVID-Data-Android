object Libs
{
    // AnyChart
    const val anychart = "com.github.AnyChart:AnyChart-Android:1.1.2"

    // Conductor
    private const val conductorVersion = "3.0.0-rc5"
    const val conductor =  "com.bluelinelabs:conductor:$conductorVersion"
    const val conductorViewPager = "com.bluelinelabs:conductor-viewpager:$conductorVersion"

    // ConstraintLayout
    private const val constraintVersion = "2.0.0-beta6"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintVersion"

    // Dagger
    private const val daggerVersion = "2.25.3"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

    // DrawerLayout
    private const val drawerLayoutVersion = "1.1.0-rc01"
    const val drawerLayout = "androidx.drawerlayout:drawerlayout:$drawerLayoutVersion"


    // JUnit 5
    const val junit = "org.junit.jupiter:junit-jupiter-api:5.5.2"

    // Logging
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    // Material Design
    private const val materialVersion = "1.1.0-rc02"
    const val material =  "com.google.android.material:material:$materialVersion"

    // Moshi
    private const val moshiVersion = "1.9.2"
    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiVersion"

    // MPAndroidChart
    const val mpAndroidChart = "com.github.PhilJay:MPAndroidChart:v3.1.0"

    // OkHttp
    private const val okhttpVersion = "4.7.2"
    const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

    // RecyclerView
    private const val recyclerViewVersion = "1.1.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0-rc01"

    // RxBinding
    private const val rxBindingVersion = "4.0.0"
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:4.0.0"


    // RxJava
    private const val rxjavaVersion = "3.0.4"
    private const val rxAndroidVersion = "3.0.0"
    const val rxJava3 = "io.reactivex.rxjava3:rxjava:$rxjavaVersion"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:$rxAndroidVersion"

    // Retrofit
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitRxJava3Adapter = "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

}

object Project
{
    const val core = ":core"
    const val covidApi = ":CovidApi"
    const val pagerView = ":PagerView"
    const val populationApi = ":PopulationApi"
    const val selectStateView = ":SelectStateView"
    const val stateHistoricalData = ":StateHistoricalData"
    const val testedVsUntested = ":TestedVsUntested"
}