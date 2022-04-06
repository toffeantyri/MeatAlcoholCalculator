package ru.calcs.meatcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logo_splash.apply {
            scaleX = 0f
            scaleY = 0f
        }
        logo_splash.animate().setDuration(800).scaleX(1f).scaleY(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            startActivity(intent)
            finish()


        }
    }
}