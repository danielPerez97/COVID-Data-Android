package daniel.perez.core

import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseController(bundle: Bundle?): Controller(bundle)
{
    constructor(): this(null)

    val disposables = CompositeDisposable()
}