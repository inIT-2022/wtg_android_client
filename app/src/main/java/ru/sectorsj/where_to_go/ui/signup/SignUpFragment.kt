package ru.sectorsj.where_to_go.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sectorsj.where_to_go.databinding.FragmentSignUpBinding
import ru.sectorsj.where_to_go.ui.AppActivity
import ru.sectorsj.where_to_go.ui.AppBarController
import ru.sectorsj.where_to_go.ui.BottomNavController


class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as AppBarController).showAppBar()
        (requireActivity()as BottomNavController).showBottomNav()
    }
}