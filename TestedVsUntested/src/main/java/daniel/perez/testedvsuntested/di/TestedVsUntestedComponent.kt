package daniel.perez.testedvsuntested.di

import dagger.Subcomponent
import daniel.perez.testedvsuntested.ui.TestedVsUntestedController

@Subcomponent
interface TestedVsUntestedComponent
{
    @Subcomponent.Factory
    interface Factory
    {
        fun create(): TestedVsUntestedComponent
    }

    fun inject(activity: TestedVsUntestedController)
}