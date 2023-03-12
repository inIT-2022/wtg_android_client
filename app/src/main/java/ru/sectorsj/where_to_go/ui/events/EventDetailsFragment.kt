package ru.sectorsj.where_to_go.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.image.ImageAdapter
import ru.sectorsj.where_to_go.databinding.FragmentEventDetailsBinding
import ru.sectorsj.where_to_go.dto.Event
import ru.sectorsj.where_to_go.ui.events.TopEventsFragment.Companion.EVENT_KEY
import ru.sectorsj.where_to_go.utils.format.FormatUtils
import ru.sectorsj.where_to_go.utils.view.hideAppBar


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
                it.linkImage.let {
                    val linksImages = it.split("|")
                    eventDetailsImagePager.adapter = ImageAdapter(this@EventDetailsFragment, linksImages)
                }

                eventTitle.text = it.title
                eventAddress.text = it.location.address
                eventDescription.text = it.description
                eventStartValue.text = it.startDatetime?.let {
                    FormatUtils.formatDate(it)
                }
                eventEndValue.text = it.finishDatetime?.let {
                    FormatUtils.formatDate(it)
                }
                eventDurationValue.text = getString(R.string.not_indicated)
                eventAgePolicyValue.text = getString(R.string.not_indicated)
                eventPriceValue.text =
                    if (it.price == null) getString(R.string.not_indicated)
                    else it.price.toString()


                shareButton.setOnClickListener { }
                favorButton.setOnClickListener { }
                calendarButton.setOnClickListener { }
                routeButton.setOnClickListener { }
                fullDescriptionButton.setOnClickListener {
                    binding.fullDescriptionButton.visibility = View.GONE
                    binding.eventDescription.maxLines = Int.MAX_VALUE
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
                hideAppBar()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,callback)
    }
}