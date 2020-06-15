package daniel.perez.core

import android.widget.Toast
import com.bluelinelabs.conductor.Controller

inline fun Controller.toast(message: String, length: Int = Toast.LENGTH_SHORT)
{
    Toast.makeText(this.activity, message ,length).show()
}