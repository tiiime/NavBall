package me.invoker.navibutton.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.content.getSystemService
import me.invoker.navibutton.ui.NavigateButtonView

class FloatingWindowService : Service() {

    private val wm: WindowManager by lazy { getSystemService<WindowManager>()!! }

    private val navigateButtonView: NavigateButtonView by lazy { NavigateButtonView(baseContext) }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        createLayoutParam()
    }

    override fun onDestroy() {
        super.onDestroy()
        wm.removeView(navigateButtonView)
    }


    private fun createLayoutParam() {
        val screenWidth = wm.defaultDisplay.width
        val screenHeight = wm.defaultDisplay.height
        val smallWindowParams = WindowManager.LayoutParams()

        smallWindowParams.type = 2038
        smallWindowParams.format = 1
        smallWindowParams.flags = 40
        smallWindowParams.gravity = 51
        smallWindowParams.width = 100
        smallWindowParams.height = 100
        smallWindowParams.x = screenWidth
        smallWindowParams.y = (screenHeight / 2) - 50

        wm.addView(navigateButtonView, smallWindowParams)
    }
}
