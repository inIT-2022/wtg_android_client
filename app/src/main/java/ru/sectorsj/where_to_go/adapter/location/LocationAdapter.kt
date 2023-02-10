package ru.sectorsj.where_to_go.adapter.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sectorsj.where_to_go.databinding.CardLocationBinding
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.utils.view.load

private typealias onLocationClickListener = () -> Unit

class LocationAdapter(
    private val onLocationClickListener: onLocationClickListener
) : ListAdapter<Location, LocationViewHolder>(LocationDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding =
            CardLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding, onLocationClickListener)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }
}

class LocationViewHolder(
    private val binding: CardLocationBinding,
    private val onLocationClickListener: onLocationClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        with(binding) {
            locationTitle.text = location.title
            location.linkImage?.let {
                val arr = it.split("|")
                val link = arr[0].trim()
                locationImage.load(link)
            }
        }
        binding.root.setOnClickListener {
            onLocationClickListener.invoke()
        }
    }
}

class LocationDiffCallBack : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }


}