package daniel.perez.populationapi.di

import dagger.Module
import dagger.Provides
import daniel.perez.populationapi.internal.PopulationApi
import retrofit2.Retrofit

@Module
class PopulationApiModule
{
	@Provides
	fun providePopulationService(builder: Retrofit.Builder): PopulationApi
	{

		return builder.baseUrl("https://datausa.io/api/")
			.build()
			.create(PopulationApi::class.java)
	}
}