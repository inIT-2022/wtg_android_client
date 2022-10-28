package ru.sectorsj.where_to_go.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sectorsj.where_to_go.databinding.FragmentLocationDetailsBinding
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.ui.locations.TopLocationsFragment.Companion.LOCATION_KEY
import ru.sectorsj.where_to_go.utils.format.FormatUtils
import ru.sectorsj.where_to_go.utils.view.load

class LocationDetailsFragment : Fragment() {

    lateinit var binding: FragmentLocationDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        val location: Location? = arguments?.getParcelable(LOCATION_KEY)

        location?.let {
            with(binding) {
                locationDetailsTitle.text = it.title
                if (!it.linkImage.isNullOrBlank()) {
                    locationDetailsImage.apply {
                        visibility = View.VISIBLE
                        load(it.linkImage)
                    }
                } else {
                    locationDetailsImage.visibility = View.GONE
                }
                locationDetailsDescription.text = it.description
                locationDetailsAddress.text = it.address
                locationDetailsWorkTimeStart.text = FormatUtils.formatDate(it.workTimeStart)
                locationDetailsWorkTimeEnd.text = FormatUtils.formatDate(it.workTimeEnd)
                locationDetailsLink.text = it.linkSite

                shareButton.setOnClickListener {  }
                favorButton.setOnClickListener {  }
                calendarButton.setOnClickListener {  }
                routeButton.setOnClickListener {  }
            }
        }

        return binding.root
    }

}