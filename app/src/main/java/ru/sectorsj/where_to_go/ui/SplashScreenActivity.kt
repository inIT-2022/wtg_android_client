package ru.sectorsj.where_to_go.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashScreenActivity, AppActivity::class.java))
                finish()
            },
            2000
        )
    }
}