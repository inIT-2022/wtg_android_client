package ru.sectorsj.where_to_go.ui.locations

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.adapter.image.ImageAdapter
import ru.sectorsj.where_to_go.databinding.FragmentLocationDetailsBinding
import ru.sectorsj.where_to_go.dto.Location
import ru.sectorsj.where_to_go.ui.main.MainFragment.Companion.LOCATION_KEY
import ru.sectorsj.where_to_go.utils.format.FormatUtils
import ru.sectorsj.where_to_go.utils.view.setFullDescriptionButtonVisibility
import ru.sectorsj.where_to_go.utils.view.shareText

@AndroidEntryPoint
class LocationDetailsFragment : Fragment() {

    lateinit var binding: FragmentLocationDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        val location: Location? = arguments?.getParcelable(LOCATION_KEY)

        location?.let { location1 ->
            with(binding) {

                locationDetailsTitle.text = location1.title
                if (!location1.linkImage.isNullOrBlank()) {
                    locationDetailsImagePager.apply {
                        val images = location1.linkImage.split("|")
                        visibility = View.VISIBLE
                        adapter = ImageAdapter(this@LocationDetailsFragment, images)
                    }
                } else {
                    locationDetailsImagePager.visibility = View.GONE
                }

                locationDetailsTitle.text = location1.title
                locationDetailsAddress.text = location1.address
                locationDetailsDescription.text = location1.description

                if (location1.workTimeStart == null && location1.workTimeEnd == null) {
                    locationWorkWeekend.visibility = View.GONE
                    locationWorkBreak.visibility = View.GONE
                }

                locationWorkDays.text =
                    if (location1.workTimeStart.isNullOrBlank()) getString(R.string.not_indicated)
                    else getString(
                        R.string.location_work_days,
                            FormatUtils.formatTime(location1.workTimeStart),
                        location1.workTimeEnd?.let { workTimeEnd ->
                            FormatUtils.formatTime(
                                workTimeEnd
                            )
                        }
                    )
                locationWorkWeekend.text = getString(
                    R.string.location_work_weekend,
                    location1.workTimeStart?.let { workTimeStart ->
                        FormatUtils.formatTime(
                            workTimeStart
                        )
                    },
                    location1.workTimeEnd?.let { workTimeEnd -> FormatUtils.formatTime(workTimeEnd) }
                )
                locationWorkBreak.text =
                    if (location1.workBreakStart.isNullOrBlank() && location1.workBreakEnd.isNullOrBlank()) getString(
                        R.string.not_indicated
                    )
                    else getString(R.string.location_work_break,
                        location1.workBreakStart?.let { startTime ->
                            FormatUtils.formatTime(
                                startTime
                            )
                        },
                        location1.workBreakEnd?.let { endTime -> FormatUtils.formatTime(endTime) })

                locationAgePolicyValue.text = getString(R.string.not_indicated)
                locationPriceValue.text = getString(R.string.not_indicated)
                locationAverageCheckValue.text = getString(R.string.not_indicated)

                setFullDescriptionButtonVisibility(
                    btn = fullDescriptionButton,
                    textView = locationDetailsDescription
                )

                locationSource.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location1.linkSite))
                    startActivity(intent)
                }

                shareButton.setOnClickListener {
                    location1.fullDescription?.let {
                        shareText(it)
                    }
                }

                favorButton.setOnClickListener { }
                calendarButton.setOnClickListener { }
                routeButton.setOnClickListener { }
                fullDescriptionButton.setOnClickListener {
                    fullDescriptionButton.visibility = View.GONE
                    locationDetailsDescription.maxLines = Int.MAX_VALUE
                }
            }
        }
        return binding.root
    }
}