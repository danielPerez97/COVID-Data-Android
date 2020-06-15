package daniel.perez.coviddata

import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import daniel.perez.coviddata.databinding.ActivityMainBinding
import daniel.perez.core.BaseActivity
import daniel.perez.selectstateview.StateSelectController

class MainActivity : BaseActivity()
{
    private lateinit var router: Router
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router = Conductor.attachRouter(this, binding.controllerContainer, savedInstanceState)
        if(!router.hasRootController())
        {
//            router.setRoot(RouterTransaction.with(HomeController()))
            router.setRoot(RouterTransaction.with(
                StateSelectController()
            ))
        }
    }

    override fun onBackPressed()
    {
        if( !router.handleBack() )
        {
            super.onBackPressed()
        }
    }
}