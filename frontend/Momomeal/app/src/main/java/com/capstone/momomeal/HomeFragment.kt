package com.capstone.momomeal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.capstone.momomeal.databinding.FragmentHomeBinding
import com.capstone.momomeal.feature.BaseFragment
import com.capstone.momomeal.feature.Category
import com.capstone.momomeal.feature.Chatroom
import com.capstone.momomeal.feature.adapter.ChatroomAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val TAG = "HomeFragment"
    private lateinit var mainActivity: MainActivity
    private val createChatFragment = CreateChatFragment()
    private val SearchResultFragment = SearchResultFragment()
    private val SearchResultCategoryFragment = SearchResultCategoryFragment()
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { Address ->
            if (Address.resultCode == Activity.RESULT_OK) {
                Address.data?.let {
                    binding.fragmentHomeEditAddress.text = it.getStringExtra("data")
                }
            }
        }
    val chatroomList = arrayListOf<Chatroom>(
        //test
        Chatroom("Bhc 뿌링클 뿌개실분 ~ ", 123, Category.Chicken, 3, "국민대학교 정문", 3.3, listOf(7, 49, 89)),
        Chatroom("밤 12시에 족발 먹을 사람 있니?", 128, Category.BoiledPork, 3, "서울대입구 4번출구", 3.9, listOf(3, 29, 69)),
        Chatroom("분식 먹을 돼지만", 128, Category.Snackbar, 3, "먹자골목", 3.9, listOf(3, 29, 69)),
        Chatroom("스타벅스 새로나온 케이크 먹자", 128, Category.CafeAndDesert, 3, "강남역 4번출구", 3.9, listOf(3, 29, 69)),
        Chatroom("배고픈데 잠이 오니?", 128, Category.Pizza, 3, "마포대표 근처", 3.9, listOf(3, 29, 69)),
        Chatroom("먹고 죽자", 128, Category.Korean, 3, "성북구 길음1동 삼부아파트", 3.9, listOf(3, 29, 69)),
        Chatroom("중국집 시켜먹을 사람 컴", 128, Category.Chinese, 3, "인천 차이나타운", 3.9, listOf(3, 29, 69))
    )

    // onCreate 이후 화면을 구성하는 코드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("system","home Oncreate is called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("system","home OncreateView is called")
        val retView = super.onCreateView(inflater, container, savedInstanceState)

        binding.fragmentHomeEditAddress.setOnClickListener{
            startForResult.launch(Intent(requireActivity().application, MyAddressActivity::class.java))
        }
        val chatAdapter = ChatroomAdapter(requireContext())
        binding.fragmentHomeRecycler.adapter = chatAdapter
        chatAdapter.replaceData(chatroomList)

        mainActivity = (activity as MainActivity)
        binding.fabHome.setOnClickListener {
            createChatFragment.show(mainActivity.supportFragmentManager, createChatFragment.tag)
        }
        initSearch(binding.fragmentHomeSearchbar)
        initTable(binding.fragmentHomeCategoryTable)
        return retView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initSearch(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                SearchResultFragment.show(mainActivity.supportFragmentManager, SearchResultFragment.tag)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initTable(table: TableLayout){
        val row = table.childCount-1
        for(i in 0..row){
            val column: TableRow = table.getChildAt(i) as TableRow
            for(j in 0 until column.childCount){
                val tv : TextView = column.getChildAt(j) as TextView
                tv.setOnClickListener{
                    val bundle = bundleOf("category" to tv.text.toString())
                    SearchResultCategoryFragment.arguments = bundle
                    SearchResultCategoryFragment.show(mainActivity.supportFragmentManager, SearchResultCategoryFragment.tag)
                }
            }
        }
    }
}
