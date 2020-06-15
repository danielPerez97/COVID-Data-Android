package daniel.perez.testedvsuntested

import daniel.perez.core.StateKey
import daniel.perez.covidapi.CovidService
import daniel.perez.covidapi.StateData
import daniel.perez.populationapi.Population
import daniel.perez.populationapi.StateMapper
import daniel.perez.populationapi.internal.PopulationApi
import daniel.perez.testedvsuntested.model.ViewStateData
import daniel.perez.testedvsuntested.ui.SubmitUiEvent
import daniel.perez.testedvsuntested.ui.SubmitUiModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TestedVsUntestedViewModel @Inject constructor(
	private val covidService: CovidService,
	private val populationService: PopulationApi
)
{

	fun uiEvents(events: Observable<SubmitUiEvent>): Observable<SubmitUiModel>
	{
		return events.flatMap {
			return@flatMap when (it)
			{
				is SubmitUiEvent.RequestData ->
				{
					Observable.zip(getTestedVsUntestedData(it.state), getPopulationData(it.state),
						BiFunction { tested: StateData, population: Population ->
							val untestedPop = population.population - (tested.positive!! + tested.negative!!)
							return@BiFunction SubmitUiModel.Success(ViewStateData(tested.positive!!, tested.negative!!, untestedPop))
						})
						.map<SubmitUiModel> { it }
						.onErrorReturn { SubmitUiModel.Failure(it) }
						.startWithItem(SubmitUiModel.InTransit)
				}
			}
		}
			.observeOn(AndroidSchedulers.mainThread())
	}

	private fun getTestedVsUntestedData(state: StateKey): Observable<StateData>
	{
		return covidService.currentState(state.name.toLowerCase(Locale.ENGLISH))
	}

	private fun getPopulationData(state: StateKey): Observable<Population>
	{
		return populationService.getStatePopulation()
			.map {
				it.list.find {
					val key = StateMapper.map(state)

					key.name == it.state.toUpperCase().replace(" ", "_")
				}
			}
	}
}