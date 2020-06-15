package daniel.perez.core

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity()
{
    val disposables = CompositeDisposable()
}