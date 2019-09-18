package com.covirtue.hng.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.covirtue.hng.R
import com.covirtue.hng.models.LoginModel
import com.covirtue.hng.services.DataServices
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        alreadyAMember.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun signUpBtnClicked(view: View) {
        if (regName.text.toString().isNotEmpty()) {
            if (regEmail.text.toString().isNotEmpty()) {
                if (regUsername.text.toString().isNotEmpty()) {
                    if (regPassword.text.isNotEmpty()) {
                        if (regConfirmPassword.text.toString().isNotEmpty()) {
                            if (regPassword.text.toString() == regConfirmPassword.text.toString()) {
                                if (Patterns.EMAIL_ADDRESS.matcher(regEmail.text.toString()).matches()) {
                                    val data = LoginModel(
                                        regName.text.toString(),
                                        regUsername.text.toString(),
                                        regEmail.text.toString(),
                                        regPassword.text.toString()
                                    )
                                    DataServices.userDataGlobal = data
                                    if (DataServices.saveLoginDetails(this, true)) {
                                        Toast.makeText(this, "Account successfully created", Toast.LENGTH_LONG).show()
                                        startActivity(Intent(this, MainActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(this, "unable to create account", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Toast.makeText(this, "Invalid email address", Toast.LENGTH_LONG).show()
                                }
                            } else {
                                Toast.makeText(this, "Password mismatch", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            regConfirmPassword.error = "required"
                        }
                    } else {
                        regPassword.error = "required"
                    }
                } else {
                    regUsername.error = "required"
                }
            } else {
                regEmail.error = "required"
            }
        } else {
            regName.error = "required"
        }
    }
}
