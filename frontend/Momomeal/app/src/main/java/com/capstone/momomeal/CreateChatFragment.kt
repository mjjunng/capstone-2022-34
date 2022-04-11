package com.capstone.momomeal

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.capstone.momomeal.databinding.FragmentCreateChatBinding
import com.capstone.momomeal.feature.BaseDialogFragment
import com.capstone.momomeal.feature.BaseFragment
import java.lang.reflect.Field

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

class CreateChatFragment : BaseDialogFragment<FragmentCreateChatBinding>(FragmentCreateChatBinding::inflate) {
    private val TAG = "CreateChatFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retView = super.onCreateView(inflater, container, savedInstanceState)
        // Spinner Setting
        setupCategorySpinner()
//        binding.spnChatCategory.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }

        setupMaxCapacitySpinner()
//        binding.spnChatMaxCapacity.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }
        // 뒤로 가는 버튼에 대한 클릭 함수
        binding.btnBack.setOnClickListener {
            dismiss()
        }

        return retView
    }

    private fun setupCategorySpinner() {
        val categoryItem = resources.getStringArray(R.array.spn_categoty_array)
        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categoryItem)
        binding.spnChatCategory.setAdapter(categoryAdapter)
    }

    private fun setupMaxCapacitySpinner() {
        val maxCapacityItem = resources.getStringArray(R.array.spn_max_capacity_array)
        val maxCapacityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, maxCapacityItem)
        binding.spnChatMaxCapacity.setAdapter(maxCapacityAdapter)
    }
}