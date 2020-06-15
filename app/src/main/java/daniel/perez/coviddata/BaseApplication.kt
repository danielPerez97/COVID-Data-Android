package daniel.perez.coviddata

import android.app.Application
import daniel.perez.coviddata.di.AppComponent
import daniel.perez.coviddata.di.AppModule
import daniel.perez.coviddata.di.DaggerAppComponent
import daniel.perez.selectstateview.di.StateSelectComponent
import daniel.perez.selectstateview.di.StateSelectComponentProvider
import daniel.perez.statehistoricaldata.di.StateHistoricalDataComponent
import daniel.perez.statehistoricaldata.di.StateHistoricalDataProvider
import daniel.perez.testedvsuntested.di.TestedVsUntestedComponent
import daniel.perez.testedvsuntested.di.TestedVsUntestedProvider
import timber.log.Timber

class BaseApplication : Application(),
    TestedVsUntestedProvider,
    StateHistoricalDataProvider,
        StateSelectComponentProvider
{
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }


        appComponent = DaggerAppComponent.builder()
            .appModule( AppModule(this) )
            .build()
    }

    override fun provideTestedVsUntestedComponent(): TestedVsUntestedComponent = appComponent
        .provideTestedVsUntestedComponent()
        .create()

    override fun provideStateHistoricalDataComponent(): StateHistoricalDataComponent = appComponent
        .provideStateHistoricalDataComponent()
        .create()

    override fun provideStateSelectComponentProvider(): StateSelectComponent = appComponent
        .provideStateSelectComponent()
        .create()
}