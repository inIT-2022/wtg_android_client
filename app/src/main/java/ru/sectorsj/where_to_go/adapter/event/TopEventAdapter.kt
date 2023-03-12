package ru.sectorsj.where_to_go.adapter.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CardTopEventBinding
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.enumeration.Month
import ru.sectorsj.where_to_go.utils.view.load
import java.time.LocalDateTime

typealias OnEventClickListener = (Event) -> Unit

class TopEventAdapter(private val onEventClickListener: OnEventClickListener):
    PagingDataAdapter<Event, TopEventViewHolder>(TopEventDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopEventViewHolder {
        val binding = CardTopEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopEventViewHolder(binding, onEventClickListener)
    }

    override fun onBindViewHolder(holder: TopEventViewHolder, position: Int) {
        val event = getItem(position)
        event?.let {
            holder.bind(it)
        }
    }

}

class TopEventViewHolder(
    private val binding: CardTopEventBinding,
    private val onEventClickListener: OnEventClickListener
):
    RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            val date =  LocalDateTime.parse(event.startDatetime)
            with(binding) {
                eventTitle.text = event.title
                eventLocation.text = event.location.title
                eventDay.text = date.dayOfMonth.toString()
                eventMonth.text = Month.calcMonth(date.month.name)
                eventImage.load(event.linkImage)
            }
            binding.root.setOnClickListener {
                onEventClickListener.invoke(event)
            }
        }
}

class TopEventDiffCallBack: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

}

