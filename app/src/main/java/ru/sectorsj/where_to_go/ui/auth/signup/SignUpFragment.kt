package ru.sectorsj.where_to_go.ui.auth.signup

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.auth.WtgAppAuth
import ru.sectorsj.where_to_go.databinding.FragmentSecurityPolicyDialogBinding
import ru.sectorsj.where_to_go.databinding.FragmentSignUpBinding
import ru.sectorsj.where_to_go.model.Registration
import ru.sectorsj.where_to_go.ui.AppBarController
import ru.sectorsj.where_to_go.ui.BottomNavController
import ru.sectorsj.where_to_go.utils.DateInputMask
import ru.sectorsj.where_to_go.utils.format.StringUtils
import ru.sectorsj.where_to_go.utils.view.checkEditFields
import ru.sectorsj.where_to_go.utils.view.hideAppBar
import ru.sectorsj.where_to_go.utils.view.showToast
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    @Inject lateinit var auth: WtgAppAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        DateInputMask.listenDateInputFrom(binding.userBirthdate)
        binding.regButton.setOnClickListener {
            if(checkEditFields(binding.root)) {
                return@setOnClickListener
            }

            if (!StringUtils.emailValidation(binding.userEmail.text.toString())) {
                showToast(getString(R.string.notify_email))
                return@setOnClickListener
            }

            if (!StringUtils.passwordValidation(binding.userPassword.text.toString())) {
                showToast(getString(R.string.notify_pass))
                return@setOnClickListener
            }

            with(binding) {
                if (userPassword.text.toString() != userRepeatPassword.text.toString()) {
                    showToast(getString(R.string.notify_pass_not_equals))
                    return@setOnClickListener
                }
                signUpViewModel.signUp(
                    Registration(
                        login = userLogin.text.toString(),
                        email = userEmail.text.toString(),
                        password = userPassword.text.toString(),
                        firstName = userName.text.toString(),
                        lastName = userSurname.text.toString(),
                        birthdayDate = userBirthdate.text.toString()
                    )
                )
            }
        }

        binding.securityPolicyTxt.setOnClickListener {
            if (binding.securityPolicyCheckBox.isChecked) {
                return@setOnClickListener
            }
            securityPolicyDialog()
        }

        lifecycleScope.launchWhenCreated {
            signUpViewModel.data.collectLatest { user ->
                auth.setAuth(user.email)
                if(user.email != null) {
                    findNavController().navigateUp()
                }
            }
        }

        binding.securityPolicyCheckBox.setOnCheckedChangeListener { _, isChecked ->
            signUpViewModel.checkPolicy.value = isChecked
        }

        lifecycleScope.launchWhenCreated {
            signUpViewModel.checkPolicy.collectLatest {
                binding.securityPolicyCheckBox.isChecked = it
            }
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as AppBarController).showAppBar()
        (requireActivity() as BottomNavController).showBottomNav()
    }


    private fun securityPolicyDialog() {
        val listener = object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which) {
                    Dialog.BUTTON_POSITIVE -> binding.securityPolicyCheckBox.isChecked = true
                    Dialog.BUTTON_NEGATIVE -> showToast(getString(R.string.security_policy_notice))
                }
            }
        }
        val dialogBinding = FragmentSecurityPolicyDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(R.string.security_policy)
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.security_policy_accept),listener)
            .setNegativeButton(getString(R.string.security_policy_decline),listener)
            .create()
        dialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.mainFragment, false)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,callback)
    }
}