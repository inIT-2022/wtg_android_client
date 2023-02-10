package ru.sectorsj.where_to_go.adapter.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CardEventBinding
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.utils.view.load

private typealias onEventClickListener = () -> Unit

class EventAdapter(private val onEventClickListener: onEventClickListener): ListAdapter<Event, EventViewHolder>(
    EventDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = CardEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, onEventClickListener)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

}

class EventViewHolder(
    private val binding: CardEventBinding,
    private val onEventClickListener: onEventClickListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(event: Event) {
        with(binding) {
            eventTitle.text = event.title
            event.location.linkImage?.let {
                val linkImages = it.split("|")
                val linkImage = linkImages[0].trim()
                eventImage.load(linkImage)
            }
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