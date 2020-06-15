package daniel.perez.covidapi.di

import dagger.Module
import dagger.Provides
import daniel.perez.covidapi.CovidService
import retrofit2.Retrofit

@Module
class CovidApiModule
{
    @Provides
    fun provideCovidService(retrofit: Retrofit.Builder): CovidService
    {
        return retrofit
            .baseUrl("https://covidtracking.com/api/")
            .build()
            .create(CovidService::class.java)
    }
}