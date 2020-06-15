package daniel.perez.selectstateview.adapter

import androidx.recyclerview.widget.SortedList
import daniel.perez.selectstateview.StateSelectViewModel

class StateSortedListCallback(val adapter: SelectionAdapter): SortedList.Callback<ViewStateModel>()
{
	override fun onInserted(position: Int, count: Int)
	{
		adapter.notifyItemRangeInserted(position, count)
	}

	override fun onRemoved(position: Int, count: Int)
	{
		adapter.notifyItemRangeRemoved(position, count)
	}

	override fun onMoved(fromPosition: Int, toPosition: Int)
	{
		adapter.notifyItemMoved(fromPosition, toPosition)
	}

	override fun onChanged(position: Int, count: Int)
	{
		adapter.notifyItemRangeChanged(position, count)
	}

	override fun compare(o1: ViewStateModel, o2: ViewStateModel): Int
	{
		return StateComparator.compare(o1, o2)
	}

	override fun areContentsTheSame(oldItem: ViewStateModel, newItem: ViewStateModel): Boolean
	{
		return oldItem.state == newItem.state
	}

	override fun areItemsTheSame(first: ViewStateModel, second: ViewStateModel): Boolean
	{
		return first == second
	}

	companion object StateComparator: Comparator<ViewStateModel>
	{
		override fun compare(first: ViewStateModel, second: ViewStateModel): Int
		{
			return first.state.compareTo(second.state)
		}

	}
}