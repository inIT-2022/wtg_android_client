package ru.sectorsj.where_to_go.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashScreenActivity, AppActivity::class.java))
                finish()
            },
            2000
        )
    }
}