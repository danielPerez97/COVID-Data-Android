package daniel.perez.statehistoricaldata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.bluelinelabs.conductor.Controller
import daniel.perez.core.StateKey
import daniel.perez.core.StateStringMapper
import daniel.perez.statehistoricaldata.databinding.StateHistoricalDataBinding
import daniel.perez.statehistoricaldata.di.StateHistoricalDataProvider
import daniel.perez.statehistoricaldata.model.SubmitUiEvent
import daniel.perez.statehistoricaldata.model.SubmitUiModel
import daniel.perez.statehistoricaldata.model.ViewStateData
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class StateHistoricalDataController(args: Bundle): Controller(), Consumer<SubmitUiModel>
{
	private val state: StateKey
	private val uiEvents = PublishSubject.create<SubmitUiEvent>()
	private lateinit var binding: StateHistoricalDataBinding
	private var currentData: List<ViewStateData>? = null

	@Inject lateinit var viewModel: StateHistoricalDataViewModel

	init
	{
		state = args.getSerializable(STATE_KEY) as StateKey
	}

	constructor(state: StateKey): this(Bundle().apply {
		putSerializable(STATE_KEY, state)
	})

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View
	{
		(activity!!.application as StateHistoricalDataProvider)
			.provideStateHistoricalDataComponent()
			.inject(this)

		binding = StateHistoricalDataBinding.inflate(inflater, container, false)
		APIlib.getInstance().setActiveAnyChartView(binding.chartView)

		viewModel.events(uiEvents)
			.subscribe(this)

		uiEvents.onNext(SubmitUiEvent.RequestData(state))

		return binding.root
	}

	override fun onAttach(view: View)
	{
		if(currentData != null)
		{
			Timber.i("OnAttach")
			APIlib.getInstance().setActiveAnyChartView(binding.chartView)
			configureChart(currentData!!)
		}
	}

	override fun accept(uiModel: SubmitUiModel)
	{
		when(uiModel)
		{
			is SubmitUiModel.Success ->
			{
				currentData = uiModel.stateData
				val chart = configureChart(uiModel.stateData)
				binding.chartView.setChart(chart)

				binding.errorText.visibility = View.GONE
				binding.progressBar.visibility = View.GONE
				binding.chartView.visibility = View.VISIBLE
				Timber.i("Success")
			}
			is SubmitUiModel.Failure ->
			{
				binding.chartView.visibility = View.GONE
				binding.progressBar.visibility = View.GONE
				binding.errorText.visibility = View.VISIBLE
				binding.errorText.text = uiModel.e.localizedMessage
				Timber.e(uiModel.e)

			}
			SubmitUiModel.InTransit ->
			{
				binding.chartView.visibility = View.GONE
				binding.errorText.visibility = View.GONE
				binding.progressBar.visibility = View.VISIBLE
				Timber.i("In Transit")
			}
		}
	}

	private fun configureChart(stateData: List<ViewStateData>): Cartesian
	{
		val reversed = stateData.asReversed()

		val viewData: List<ValueDataEntry> = reversed.map {
			ValueDataEntry(it.date, it.positive)
		}

		val xAverage = reversed.sumBy { it.positive.toInt() } / reversed.size

		val lineViewData = ValueDataEntry("Line of best fit", xAverage)

		val columnChart = AnyChart.column()
		with(columnChart)
		{
			data(viewData)
			title("Historical Cases in ${StateStringMapper.map(state)}")
			labels().position("outside")

			legend().title().enabled(true)
			legend().title()
				.text("Populations")

		}

		return columnChart
	}

	companion object
	{
		/**
		 * Use this to tell what state you want to use.
		 * Use two uppercase letters to represent the state i.e. AR, AK, CA, CN, etc.
		 */
		const val STATE_KEY = "STATE_KEY"
	}

}