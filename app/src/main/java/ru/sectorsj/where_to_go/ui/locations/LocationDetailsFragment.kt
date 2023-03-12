package ru.sectorsj.where_to_go.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.image.ImageAdapter
import ru.sectorsj.where_to_go.databinding.FragmentLocationDetailsBinding
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.ui.locations.TopLocationsFragment.Companion.LOCATION_KEY
import ru.sectorsj.where_to_go.utils.format.FormatUtils

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
                    locationDetailsImagePager.apply {
                        val images = it.linkImage.split("|")
                        visibility = View.VISIBLE
                        adapter = ImageAdapter(this@LocationDetailsFragment, images)
                       /* beginFakeDrag()
                        fakeDragBy(-10f)
                        endFakeDrag() */
                    }
                } else {
                    locationDetailsImagePager.visibility = View.GONE
                }

                locationDetailsTitle.text = it.title
                locationDetailsAddress.text = it.address
                locationDetailsDescription.text = it.description
                locationWorkDays.text = getString(
                    R.string.location_work_days,
                    it.workTimeStart?.let { workTimeStart -> FormatUtils.formatTime(workTimeStart) },
                    it.workTimeEnd?.let { workTimeEnd -> FormatUtils.formatTime(workTimeEnd) }
                )
                locationWorkWeekend.text = getString(
                    R.string.location_work_weekend,
                    it.workTimeStart?.let { workTimeStart -> FormatUtils.formatTime(workTimeStart) },
                    it.workTimeEnd?.let { workTimeEnd -> FormatUtils.formatTime(workTimeEnd) }
                )
                locationWorkBreak.text =
                    if (it.workBreakStart.isNullOrBlank() && it.workBreakEnd.isNullOrBlank()) getString(R.string.not_indicated)
                    else getString(R.string.location_work_break,
                        it.workBreakStart?.let { startTime -> FormatUtils.formatTime(startTime) },
                        it.workBreakEnd?.let { endTime -> FormatUtils.formatTime(endTime) })

                locationAgePolicyValue.text = getString(R.string.not_indicated)
                locationPriceValue.text = getString(R.string.not_indicated)
                locationAverageCheckValue.text = getString(R.string.not_indicated)


                shareButton.setOnClickListener { }
                favorButton.setOnClickListener { }
                calendarButton.setOnClickListener { }
                routeButton.setOnClickListener { }
                fullDescriptionButton.setOnClickListener {
                    binding.fullDescriptionButton.visibility = View.GONE
                    binding.locationDetailsDescription.maxLines = Int.MAX_VALUE
                }
            }
        }
        return binding.root
    }


}