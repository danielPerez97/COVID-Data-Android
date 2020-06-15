package daniel.perez.statehistoricaldata.di

import dagger.Subcomponent
import daniel.perez.statehistoricaldata.StateHistoricalDataController

@Subcomponent
interface StateHistoricalDataComponent
{
	@Subcomponent.Factory
	interface Factory
	{
		fun create(): StateHistoricalDataComponent
	}

	fun inject(controller: StateHistoricalDataController)
}
