package com.covirtue.hng.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.covirtue.hng.R
import com.covirtue.hng.services.DataServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataServices.init(this)

        if (DataServices.isLogin && DataServices.userDataGlobal != null) {
            val userData = DataServices.userDataGlobal!!
            username.text = userData.username
            full_name.text = userData.name
            email.text = userData.email
        } else {
            Toast.makeText(this, "Please login", Toast.LENGTH_LONG).show()
            this.finnishActivity()
        }


        logoutBtnClicked.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Do you want to logout")
                .setPositiveButton("Yes") { dialogInterface, i ->
                    dialogInterface.dismiss()
                    DataServices.saveLoginDetails(this, false)
                    Toast.makeText(this, "You are now signed out", Toast.LENGTH_LONG).show()
                    this.finnishActivity()
                }
                .setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .setCancelable(false)
                .show()
        }

    }

    private var exitAppPressed : Boolean = false
    override fun onBackPressed() {
        if (exitAppPressed) {
            super.onBackPressed()
        } else {
            exitAppPressed = true
            Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                if (!isFinishing) {
                    exitAppPressed = false
                }
            }, 4000)
        }
    }

    private fun finnishActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
