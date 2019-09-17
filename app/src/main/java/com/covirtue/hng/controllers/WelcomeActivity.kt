package com.covirtue.hng.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.covirtue.hng.R
import com.covirtue.hng.services.DataServices
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        DataServices.init(this)


        Handler().postDelayed({
            if (DataServices.isLogin) {
                if (DataServices.userDataGlobal != null) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2500)

        Handler().postDelayed({
            progressBar.visibility = View.VISIBLE
        }, 500)
    }
}
