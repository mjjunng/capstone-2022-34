package com.capstone.momomeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.capstone.momomeal.databinding.FragmentSearchResultCategoryBinding
import com.capstone.momomeal.feature.BaseDialogFragment
import com.capstone.momomeal.feature.Category
import com.capstone.momomeal.feature.Chatroom
import com.capstone.momomeal.feature.adapter.ChatroomAdapter

class SearchResultCategoryFragment : BaseDialogFragment<FragmentSearchResultCategoryBinding>(
    FragmentSearchResultCategoryBinding::inflate) {

    private val TAG = "SearchResultCategoryFragment"
    val chatlist = arrayListOf<Chatroom>(
        //test
        Chatroom("Bhc 뿌링클 뿌개실분 ~ ", 123, Category.Chicken, 3, "국민대학교 정문", 3.3, listOf(7, 49, 89)),
        Chatroom("밤 12시에 족발 먹을 사람 있니?", 128, Category.BoiledPork, 3, "서울대입구 4번출구", 3.9, listOf(3, 29, 69))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val retView = super.onCreateView(inflater, container, savedInstanceState)
        binding.fragmentSearchResultCategoryBack.setOnClickListener{
            dismiss()
        }
        val title = arguments?.getString("category")
        binding.fragmentSearchResultCategoryTitle.text = title
        val chatroomadapter = ChatroomAdapter(requireContext())
        binding.fragmentSearchResultCategoryRecycle.adapter = chatroomadapter
        chatroomadapter.replaceData(chatlist)
        return retView
    }
}