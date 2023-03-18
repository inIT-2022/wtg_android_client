package ru.sectorsj.where_to_go.ui.rest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sectorsj.where_to_go.databinding.FragmentRestBinding

class RestFragment : Fragment() {

    lateinit var binding: FragmentRestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestBinding.inflate(inflater, container, false)

        with(binding) {
            restDateBtn.setOnClickListener { }
            restTimeBtn.setOnClickListener { }
            restViewBtn.setOnClickListener { }
            restCategoryBtn.setOnClickListener { }
            restPlaceBtn.setOnClickListener { }
            restEventBtn.setOnClickListener { }
            restPriceBtn.setOnClickListener { }
            restSomethingElseBtn.setOnClickListener { }
            restShareGeolocationBtn.setOnClickListener { }
            restInviteToEventBtn.setOnClickListener { }
            restVehicleSearchBtn.setOnClickListener { }
        }
        return binding.root
    }

}