package ru.sectorsj.where_to_go.ui.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.databinding.FragmentSignInBinding
import ru.sectorsj.where_to_go.ui.AppBarController
import ru.sectorsj.where_to_go.ui.BottomNavController

class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as AppBarController).showAppBar()
        (requireActivity() as BottomNavController).showBottomNav()
    }

}