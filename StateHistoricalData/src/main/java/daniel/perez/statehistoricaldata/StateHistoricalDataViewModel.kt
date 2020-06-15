package daniel.perez.statehistoricaldata

import daniel.perez.core.StateKey
import daniel.perez.covidapi.CovidService
import daniel.perez.statehistoricaldata.model.SubmitUiEvent
import daniel.perez.statehistoricaldata.model.SubmitUiModel
import daniel.perez.statehistoricaldata.model.ViewStateData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject

class StateHistoricalDataViewModel @Inject constructor(
	private val covidService: CovidService
)
{
	fun events(events: Observable<SubmitUiEvent>): Observable<SubmitUiModel>
	{
		return events.flatMap {
			return@flatMap when(it)
			{
				is SubmitUiEvent.RequestData ->
				{
					provideStateHistory(it.state)
						.map <SubmitUiModel> { SubmitUiModel.Success(it) }
						.onErrorReturn { SubmitUiModel.Failure(it) }
						.startWithItem(SubmitUiModel.InTransit)
				}
			}
		}
			.observeOn(AndroidSchedulers.mainThread())
	}

	private fun provideStateHistory(state: StateKey): Observable<List<ViewStateData>>
	{
		return covidService.stateHistory(state.name.toLowerCase(Locale.ENGLISH))
			.map {
				it.map { ViewStateData(it.positive, it.date) }
			}
	}
}