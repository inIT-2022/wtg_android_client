package ru.sectorsj.where_to_go.ui

import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.auth.WtgAppAuth
import ru.sectorsj.where_to_go.databinding.ActivityAppBinding
import ru.sectorsj.where_to_go.ui.auth.AuthViewModel
import ru.sectorsj.where_to_go.utils.view.hide
import ru.sectorsj.where_to_go.utils.view.setListener
import ru.sectorsj.where_to_go.utils.view.show
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)
class AppActivity : BaseActivity(), BottomNavController {
    @Inject lateinit var auth: WtgAppAuth
    lateinit var binding: ActivityAppBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val bottomNav = binding.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        navController?.let {
            bottomNav.setListener(navController, this@AppActivity)
        }
        //window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        lifecycleScope.launchWhenCreated {
            authViewModel.data.collectLatest {
                invalidateOptionsMenu()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        menu.let {
            it?.setGroupVisible(R.id.unAuth, !authViewModel.authentificated)
            it?.setGroupVisible(R.id.auth, authViewModel.authentificated)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.sign_in -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_to_signInFragment)
                hideBars()
                true
            }
            R.id.sign_up -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_to_signUpFragment)
                hideBars()
                true
            }
            R.id.sign_out -> {
                auth.removeAuth()
                true
            }
            else -> false
        }
    }

    private fun hideBars() {
        hideAppBar()
        hideBottomNav()
    }

    override fun showBottomNav() {
        binding.bottomNav.show()
    }

    override fun hideBottomNav() {
        binding.bottomNav.hide()
    }
}