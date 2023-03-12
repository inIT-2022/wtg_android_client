package ru.sectorsj.where_to_go.adapter.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CardEventBinding
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.utils.view.load

private typealias onEventClickListener = () -> Unit

class EventAdapter(private val onEventClickListener: onEventClickListener): PagingDataAdapter<Event, EventViewHolder>(
    EventDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = CardEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, onEventClickListener)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        event?.let {
            holder.bind(it)
        }
    }

}

class EventViewHolder(
    private val binding: CardEventBinding,
    private val onEventClickListener: onEventClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(event: Event) {
        with(binding) {
            eventTitle.text = event.title
            eventImage.load(event.linkImage)
        }
        binding.root.setOnClickListener {
            onEventClickListener.invoke()
        }
    }

}

class EventDiffCallBack : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

}