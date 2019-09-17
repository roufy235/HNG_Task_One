package com.covirtue.hng.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.covirtue.hng.R
import com.covirtue.hng.services.DataServices
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DataServices.init(this)

        notAMember.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }


    fun loginBtnClicked(view: View) {
        if (DataServices.userDataGlobal != null) {
            if (loginUsername.text.toString().isNotEmpty()) {
                if (loginPassword.text.toString().isNotEmpty()) {
                    if ((loginUsername.text.toString() == DataServices.userDataGlobal!!.username) && (loginPassword.text.toString() == DataServices.userDataGlobal!!.password)) {
                        DataServices.saveLoginDetails(this, true)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "invalid username and password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    loginPassword.error = "required"
                }
            } else {
                loginUsername.error = "required"
            }
        } else {
            Toast.makeText(this, "no account has been created yet", Toast.LENGTH_SHORT).show()
        }

    }
}
