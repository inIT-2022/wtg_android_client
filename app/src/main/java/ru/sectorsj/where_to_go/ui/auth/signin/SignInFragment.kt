package ru.sectorsj.where_to_go.ui.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.sectorsj.where_to_go.auth.WtgAppAuth
import ru.sectorsj.where_to_go.databinding.FragmentSignInBinding
import ru.sectorsj.where_to_go.model.Auth
import ru.sectorsj.where_to_go.ui.AppBarController
import ru.sectorsj.where_to_go.ui.BottomNavController
import ru.sectorsj.where_to_go.utils.view.checkEditFields
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding
    private val signInViewModel: SignInViewModel by viewModels()
    @Inject lateinit var auth: WtgAppAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        with(binding) {
            regButton.setOnClickListener {
                if (checkEditFields(binding.root)) {
                    return@setOnClickListener
                }
                signInViewModel.signIn(
                    Auth(
                        login = userName.text.toString(),
                        password = userPassword.text.toString()
                    )
                )
            }
        }
        lifecycleScope.launchWhenCreated {
            signInViewModel.data.collectLatest { authState ->
                auth.setAuth(authState.email)
                if (authState.email != null) {
                    findNavController().navigateUp()
                }
            }
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as AppBarController).showAppBar()
        (requireActivity() as BottomNavController).showBottomNav()
    }

}