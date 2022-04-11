package com.capstone.momomeal

import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import com.capstone.momomeal.databinding.FragmentGreetingBinding
import com.capstone.momomeal.feature.BaseFragment

class GreetingFragment : BaseFragment<FragmentGreetingBinding>(FragmentGreetingBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retView = super.onCreateView(inflater, container, savedInstanceState)

        paint(binding.fragmentGreetingTxt)
        binding.fragmentGreetingEmail.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_login_fragment_container, SignupFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.fragmentGreetingBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
        return retView
    }

    private fun paint(view: TextView){
        val text = "혼자 해결하던 식사\n이제는 다 같이 먹자!"
        val orange = ContextCompat.getColor(requireContext(), R.color.orange_deep)
        val spannable = text.toSpannable()
        spannable.setSpan(
            ForegroundColorSpan(orange),
            14,
            19,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = spannable
    }
}
