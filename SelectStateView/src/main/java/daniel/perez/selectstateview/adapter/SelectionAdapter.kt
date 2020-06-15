package daniel.perez.selectstateview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import daniel.perez.core.StateKey
import daniel.perez.core.StateStringMapper
import daniel.perez.selectstateview.databinding.StateItemBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SelectionAdapter: RecyclerView.Adapter<SelectionAdapter.ViewHolder>()
{
    private val data: List<ViewStateModel> = StateKey.values().map { ViewStateModel(it.name) }
    private val clickSubject = PublishSubject.create<ViewStateModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StateItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(data[position])
    }

    fun clicks(): Observable<ViewStateModel>
    {
        return clickSubject
            .debounce(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
    }


    inner class ViewHolder(private val binding: StateItemBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(state: ViewStateModel)
        {
            binding.stateText.text = state.state
            binding.stateText.setOnClickListener { clickSubject.onNext(state) }
        }
    }
}