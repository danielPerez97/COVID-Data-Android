package daniel.perez.coviddata.di

import dagger.Component
import daniel.perez.covidapi.di.CovidApiModule
import daniel.perez.populationapi.di.PopulationApiModule
import daniel.perez.selectstateview.di.StateSelectComponent
import daniel.perez.statehistoricaldata.di.StateHistoricalDataComponent
import daniel.perez.testedvsuntested.di.TestedVsUntestedComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CovidApiModule::class,
    PopulationApiModule::class
])
interface AppComponent
{
    fun provideTestedVsUntestedComponent(): TestedVsUntestedComponent.Factory

    fun provideStateHistoricalDataComponent(): StateHistoricalDataComponent.Factory

    fun provideStateSelectComponent():StateSelectComponent.Factory
}