package daniel.perez.pagerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.viewpager.RouterPagerAdapter
import daniel.perez.core.BaseController
import daniel.perez.core.StateKey
import daniel.perez.pagerview.databinding.ControllerPagerBinding
import daniel.perez.statehistoricaldata.StateHistoricalDataController
import daniel.perez.testedvsuntested.ui.TestedVsUntestedController
import timber.log.Timber

class PagerController(args: Bundle): BaseController()
{
	private val state: StateKey
	private lateinit var binding: ControllerPagerBinding
	private val pagerAdapter: RouterPagerAdapter

	constructor(key: StateKey): this(Bundle().apply {
		putSerializable(STATE_KEY, key)
	})

	init
	{
		state = args.getSerializable(STATE_KEY) as StateKey
		pagerAdapter = object: RouterPagerAdapter(this) {
			override fun configureRouter(router: Router, position: Int)
			{
				if(!router.hasRootController())
				{
					Timber.i("Position is $position")
					when(position)
					{
						0 -> router.setRoot(RouterTransaction.with(TestedVsUntestedController(state).apply {
							retainViewMode = RetainViewMode.RETAIN_DETACH
						}))
						1 -> router.setRoot(RouterTransaction.with(StateHistoricalDataController(state).apply {
							retainViewMode = RetainViewMode.RETAIN_DETACH
						}))
					}
				}
			}

			override fun getCount(): Int = 2

			override fun getPageTitle(position: Int): CharSequence?
			{
				return if(position == 0)
				{
					"Testing"
				}
				else
				{
					"Daily"
				}
			}

		}
	}


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View
	{
		binding = ControllerPagerBinding.inflate(inflater, container, false)
		binding.viewPager.adapter = pagerAdapter
		binding.tabLayout.setupWithViewPager(binding.viewPager)
		return binding.root
	}

	override fun onDestroyView(view: View)
	{
		if(!activity!!.isChangingConfigurations)
		{
			binding.viewPager.adapter = null
		}
		binding.tabLayout.setupWithViewPager(null)
		super.onDestroyView(view)
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