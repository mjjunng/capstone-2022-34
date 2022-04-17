package com.capstone.momomeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.momomeal.databinding.FragmentMypageBinding
import com.capstone.momomeal.feature.BaseFragment
import com.capstone.momomeal.feature.Rate
import com.capstone.momomeal.feature.Review
import java.time.LocalDateTime

class MypageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {
    private val TAG = "MypageFragment"

    // For test.
//    private val reviewList = arrayListOf<Review>(
//        Review(1, Rate.Good, tmpstr, LocalDateTime.of(2022, 1, 1, 10, 31, 22))
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val retView = super.onCreateView(inflater, container, savedInstanceState)

        return retView
    }
}
