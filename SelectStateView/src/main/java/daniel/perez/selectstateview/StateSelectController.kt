package daniel.perez.selectstateview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.jakewharton.rxbinding4.widget.textChangeEvents
import daniel.perez.core.BaseController
import daniel.perez.core.StateKey
import daniel.perez.pagerview.PagerController
import daniel.perez.selectstateview.adapter.SelectionAdapter
import daniel.perez.selectstateview.databinding.ControllerStateSelectBinding
import daniel.perez.selectstateview.di.StateSelectComponentProvider
import javax.inject.Inject

class StateSelectController(args: Bundle?): BaseController(args)
{
    constructor(): this(null)

    private val adapter = SelectionAdapter()
    private lateinit var binding: ControllerStateSelectBinding
    @Inject lateinit var viewModel: StateSelectViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup,
                              savedViewState: Bundle?): View
    {
        (activity!!.application as StateSelectComponentProvider)
            .provideStateSelectComponentProvider()
            .inject(this)

        binding = ControllerStateSelectBinding.inflate(inflater, container, false)
        with(binding.recyclerView)
        {
            val lm = LinearLayoutManager(activity)
            binding.recyclerView.adapter = this@StateSelectController.adapter
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(activity, lm.orientation))
        }

        return binding.root
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        setupClicks()
    }

    override fun onDetach(view: View)
    {
        super.onDetach(view)
        disposables.clear()
    }

    private fun setupSearch()
    {

    }

    private fun setupClicks()
    {
        disposables.add(adapter.clicks()
            .subscribe {
                router.pushController(RouterTransaction.with(PagerController(StateKey.valueOf(it.state)))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(VerticalChangeHandler()))
            })
    }

}