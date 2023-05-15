package kr.marko.photobook.init

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.marko.photobook.MainActivity
import kr.marko.photobook.databinding.ActivitySplashBinding
import kr.marko.photobook.domain.PLog
import timber.log.Timber

class InitActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        PLog.marko("SetContent ! $binding")
        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(this@InitActivity, MainActivity::class.java))
            finish()
        }
    }

}