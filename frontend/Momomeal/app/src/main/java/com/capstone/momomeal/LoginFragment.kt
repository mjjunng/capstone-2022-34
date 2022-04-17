package com.capstone.momomeal

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.capstone.momomeal.databinding.FragmentGreetingBinding
import com.capstone.momomeal.databinding.FragmentLoginBinding
import com.capstone.momomeal.design.LinearGradientSpan
import com.capstone.momomeal.feature.BaseFragment

import com.google.android.material.textfield.TextInputLayout


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        val retView = super.onCreateView(inflater, container, savedInstanceState)
        //init
        paintGradient(binding.fragmentLoginAppname)
        binding.fragmentLoginSubmit.setOnClickListener{
            if(logIn()) {
                startActivity(Intent(activity, MainActivity::class.java))
                requireActivity().finish()
            }else{
                val msg = Toast.makeText(requireActivity().applicationContext, "로그인 실패", Toast.LENGTH_SHORT)
                msg.show()
            }
        }
        binding.fragmentLoginSignUp.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_login_fragment_container, GreetingFragment())
                .addToBackStack(null)
                .commit()
            //startActivity(Intent(activity, RegisterActivity::class.java))
        }

        //auto-login
        val auto = requireActivity().getSharedPreferences("autoLogin", MODE_PRIVATE)
        val email = auto?.getString("email", null)
        val password = auto?.getString("password", null)
        val active = auto?.getBoolean("active", true)
        if(active == true){
            binding.fragmentLoginEmail.getEditText()?.setText(email)
            binding.fragmentLoginPassword.getEditText()?.setText(password)
            binding.fragmentLoginSubmit.callOnClick()
        }
        return retView
    }

    fun logIn(): Boolean {
        var email : String = binding.fragmentLoginEmail.getEditText()?.getText().toString()
        var password : String = binding.fragmentLoginPassword.getEditText()?.getText().toString()
        if(email.isEmpty() || password.isEmpty()){
            return false
        }
        if (binding.fragmentLoginAuto.isChecked) {
            // 자동 로그인 데이터 저장
            val auto = requireActivity().getSharedPreferences("autoLogin", MODE_PRIVATE)
            val autoLoginEdit = auto?.edit()
            autoLoginEdit?.putString("email", email)
            autoLoginEdit?.putString("password", password)
            autoLoginEdit?.putBoolean("active", binding.fragmentLoginAuto.isChecked)
            autoLoginEdit?.commit()
        }
        return email == "user1234" && password == "1234"
    }
    private fun paintGradient(view: TextView){
        val text = "모두모아먹자!"
        val orange = ContextCompat.getColor(requireActivity(), R.color.orange_deep)
        val yellow = ContextCompat.getColor(requireActivity(), R.color.yellow)
        val green = ContextCompat.getColor(requireActivity(), R.color.green)
        val spannable = text.toSpannable()
        spannable[0..text.length-1] = LinearGradientSpan(text, text, orange, yellow)
        spannable.setSpan(
            ForegroundColorSpan(green),
            text.length-1, // start
            text.length, // end
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = spannable
    }
}