package ru.sectorsj.where_to_go.ui

import android.os.Bundle
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.sectorsj.where_to_go.R
import ru.sectorsj.where_to_go.databinding.ActivityAppBinding
import ru.sectorsj.where_to_go.utils.view.hide
import ru.sectorsj.where_to_go.utils.view.setListener
import ru.sectorsj.where_to_go.utils.view.show

class AppActivity : BaseActivity(), BottomNavController {
    lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNav = binding.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        navController?.let {
            bottomNav.setListener(navController)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.sign_in -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.signInFragment)
                hideBars()
                true
            }
            R.id.sign_up -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.signUpFragment)
                hideBars()
                true
            }
            R.id.sign_out -> {
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