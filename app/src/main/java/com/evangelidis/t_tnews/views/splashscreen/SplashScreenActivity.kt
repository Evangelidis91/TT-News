package com.evangelidis.t_tnews.views.splashscreen

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.evangelidis.t_tnews.Constant.Companion.SPLASHSCREEN_TIME
import com.evangelidis.t_tnews.databinding.ActivitySplashScreenBinding
import com.evangelidis.t_tnews.views.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by lazy { ActivitySplashScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val handler = Handler()
        handler.postDelayed({
            startActivity(MainActivity.createIntent(this))
            finish()
        }, SPLASHSCREEN_TIME)
    }

    override fun onBackPressed() {}
}
