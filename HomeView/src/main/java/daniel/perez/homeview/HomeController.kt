package daniel.perez.homeview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.material.navigation.NavigationView
import daniel.perez.core.BaseController
import daniel.perez.homeview.databinding.ControllerHomeBinding
import timber.log.Timber

class HomeController: BaseController(), NavigationView.OnNavigationItemSelectedListener
{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View
    {
        val binding = ControllerHomeBinding.inflate(inflater, container, false)
        viewInit(binding)
        return binding.root
    }

    private fun viewInit(binding: ControllerHomeBinding)
    {
        binding.navigation.setNavigationItemSelectedListener(this)
        binding.homeText.text = "Use the sidebar"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        Timber.i( item.itemId.toString() )

        return true
    }
}