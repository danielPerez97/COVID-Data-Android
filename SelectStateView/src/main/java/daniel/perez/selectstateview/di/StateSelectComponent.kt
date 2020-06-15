package daniel.perez.selectstateview.di

import dagger.Subcomponent
import daniel.perez.selectstateview.StateSelectController
import daniel.perez.selectstateview.StateSelectViewModel

@Subcomponent
interface StateSelectComponent
{
	@Subcomponent.Factory
	interface Factory
	{
		fun create(): StateSelectComponent
	}

	fun inject(controller: StateSelectController)
}