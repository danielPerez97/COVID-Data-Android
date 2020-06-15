package daniel.perez.testedvsuntested.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import daniel.perez.core.BaseController
import daniel.perez.core.StateKey
import daniel.perez.testedvsuntested.TestedVsUntestedViewModel
import daniel.perez.testedvsuntested.databinding.ActivityTestedvsUntestedBinding
import daniel.perez.testedvsuntested.di.TestedVsUntestedProvider
import daniel.perez.testedvsuntested.model.ViewStateData
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class TestedVsUntestedController(args: Bundle) : BaseController(args), Consumer<SubmitUiModel>
{
    private val state: StateKey
    private lateinit var binding: ActivityTestedvsUntestedBinding
    private val dataRequests = PublishSubject.create<SubmitUiEvent>()
    private var currentData: ViewStateData? = null
    @Inject lateinit var viewModel: TestedVsUntestedViewModel

    init
    {
        state = args.getSerializable(STATE_KEY) as StateKey
    }

    constructor(state: StateKey): this(Bundle().apply {
        putSerializable(STATE_KEY, state)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View
    {
        (activity!!.application as TestedVsUntestedProvider)
            .provideTestedVsUntestedComponent()
            .inject(this)

        binding = ActivityTestedvsUntestedBinding.inflate(inflater, container, false)

        viewModel.uiEvents(dataRequests)
            .subscribe(this)

        dataRequests.onNext (SubmitUiEvent.RequestData( state ) )

        return binding.root
    }

    override fun onAttach(view: View)
    {
        if(currentData != null)
        {
            Timber.i("OnAttach")
            configureChart(currentData!!)
        }
    }

    override fun onDetach(view: View)
    {
        super.onDetach(view)
        binding.chartView.clear()
    }

    override fun accept(uiModel: SubmitUiModel)
    {
        Timber.i(uiModel.javaClass.toString())
        when(uiModel)
        {
            SubmitUiModel.InTransit ->
            {
                binding.chartView.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            is SubmitUiModel.Success ->
            {
                currentData = uiModel.stateData
                configureChart(uiModel.stateData)

                // Change Visibility
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.chartView.visibility = View.VISIBLE
            }
            is SubmitUiModel.Failure ->
            {
                binding.progressBar.visibility = View.GONE
                binding.chartView.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = uiModel.e.localizedMessage
                Timber.e(uiModel.e)
            }
        }
    }

    private fun configureChart(stateData: ViewStateData)
    {
        val list = listOf<PieEntry>(
            PieEntry(stateData.positive.toFloat(), "Positive"),
            PieEntry(stateData.negative.toFloat(), "Negative"),
            PieEntry(stateData.untestedPopulation.toFloat(), "Untested")
        )

        val pieDataSet = PieDataSet(list, "Tested vs Untested Populations")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        pieDataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieDataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.setAutomaticallyDisableSliceSpacing(true)
        pieDataSet.valueTextSize = 24f

        val pieData = PieData(pieDataSet)
        with(binding.chartView)
        {
            data = pieData
            legend.isEnabled = true
        }
        binding.chartView.setEntryLabelColor(Color.BLACK)
        binding.chartView.invalidate()
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