package com.capstone.momomeal


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.momomeal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_login_fragment_container, LoginFragment())
            .commit()
    }
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}