package ru.sectorsj.where_to_go.app

import android.app.Application
import ru.sectorsj.where_to_go.auth.WtgAppAuth

class WTGApplication: Application() {
    override fun onCreate() {
        WtgAppAuth.initAuth(this)
        super.onCreate()
    }
}