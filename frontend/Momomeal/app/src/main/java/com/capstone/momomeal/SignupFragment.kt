package com.capstone.momomeal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.capstone.momomeal.databinding.FragmentSignupBinding
import com.capstone.momomeal.feature.BaseFragment

class SignupFragment : BaseFragment<FragmentSignupBinding>(FragmentSignupBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retView = super.onCreateView(inflater, container, savedInstanceState)
        val checkAll = binding.fragmentSignupCheckAll
        val checkArray = arrayOf<CheckBox>(
            binding.fragmentSignupCheck1, binding.fragmentSignupCheck2
        )

        binding.fragmentSignupBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
        checkAll.setOnClickListener{
            if (checkAll.isChecked()) {
                checkArray.forEach { checkBox -> checkBox.isChecked = true}
            }
        }
        binding.fragmentSignupSubmit.setOnClickListener{
            val email = binding.fragmentSignupEmail.getEditText()?.getText().toString()
            val name = binding.fragmentSignupName.getEditText()?.getText().toString()
            val password = binding.fragmentSignupPassword.getEditText()?.getText().toString()
            val confirm = binding.fragmentSignupConfirm.getEditText()?.getText().toString()

            if(checkArray[0].isChecked && checkArray[1].isChecked){
                if(password.length > 0 && email.length>0 && name.length >0){
                    if(password == confirm){
                        Toast.makeText(requireContext(), "비밀번호가 일치함", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity, MainActivity::class.java))
                        requireActivity().finish()
                        //email, name password -> server
                    }else{
                        Toast.makeText(requireContext(), "비밀번호가 일치하지않음", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "필수요소를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "필수 약관에 동의해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        return retView
    }
}

