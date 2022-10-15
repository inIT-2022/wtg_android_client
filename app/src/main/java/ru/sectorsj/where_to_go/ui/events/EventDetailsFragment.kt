package ru.sectorsj.where_to_go.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.databinding.FragmentEventDetailsBinding
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.ui.events.TopEventsFragment.Companion.EVENT_KEY
import ru.sectorsj.where_to_go.utils.format.FormatUtils


class EventDetailsFragment : Fragment() {
   lateinit var binding: FragmentEventDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val event: Event? = arguments?.getParcelable(EVENT_KEY)
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        event?.let {
            with(binding) {
                title.text = it.title
                description.text = it.description
                it.startDatetime?.let {
                    startDateTime.text = FormatUtils.formatDate(it)
                }
                it.finishDatetime?.let {
                    endDateTime.text = FormatUtils.formatDate(it)
                }
                price.text = it.price.toString()
            }
        }
        return binding.root
    }


}