package ru.sectorsj.where_to_go.adapter.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CardTopEventBinding
import ru.sectorsj.where_to_go.dto.Event


class TopEventAdapter:
    ListAdapter<Event, TopEventViewHolder>(TopEventDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopEventViewHolder {
        val binding = CardTopEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopEventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

}

class TopEventViewHolder(
    private val binding: CardTopEventBinding
):
    RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            with(binding) {
                topEventTitle.text = event.title
                topEventDescription.text = event.description
                topEventDate.text = event.startDatetime
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
