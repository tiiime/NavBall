package me.invoker.navibutton

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.invoker.navibutton.service.FloatingWindowService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this)) {
                startService(Intent(this, FloatingWindowService::class.java))
            } else {
                Toast.makeText(this, getString(R.string.ask_for_permission), Toast.LENGTH_LONG)
                    .show()
            }
        }

        stop.setOnClickListener {
            stopService(Intent(this, FloatingWindowService::class.java))
        }

        back.setOnClickListener {
            finish()
        }
    }
}
