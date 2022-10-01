package ru.sectorsj.where_to_go.adapter.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CardTopLocationBinding
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.utils.viewUtil.load

class TopLocationAdapter:
    ListAdapter<Location, TopLocationViewHolder>(TopLocationDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopLocationViewHolder {
        val binding = CardTopLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopLocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopLocationViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }

}

class TopLocationViewHolder(
    private val binding: CardTopLocationBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        with(binding) {
            topLocationTitle.text = location.title
            topLocationDescription.text = location.description
            topLocationDate.text = location.workTimeStart
        }
        binding.locationImage.load(location.linkImage)
    }
}

class TopLocationDiffCallBack: DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }

}