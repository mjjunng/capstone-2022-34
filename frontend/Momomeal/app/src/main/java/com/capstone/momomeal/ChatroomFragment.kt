package com.capstone.momomeal

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.capstone.momomeal.databinding.FragmentChatroomBinding
import com.capstone.momomeal.feature.BaseFragment
import com.capstone.momomeal.feature.Category
import com.capstone.momomeal.feature.Chatroom
import com.capstone.momomeal.feature.adapter.ChatroomAdapter


class ChatroomFragment : BaseFragment<FragmentChatroomBinding>(FragmentChatroomBinding::inflate) {
    private val TAG = "ChatroomFragment"
    val chatroomList = arrayListOf<Chatroom>(
        //test
        Chatroom("예전 채팅 1 ", 123, Category.Chicken, 3, "국민대학교 정문", 3.3, listOf(7, 49, 89)),
        Chatroom("예전 채팅 2", 128, Category.BoiledPork, 3, "서울대입구 4번출구", 3.9, listOf(3, 29, 69)),
        Chatroom("예전 채팅 3", 128, Category.Snackbar, 3, "먹자골목", 3.9, listOf(3, 29, 69)),
        Chatroom("예전 채팅 4", 128, Category.CafeAndDesert, 3, "강남역 4번출구", 3.9, listOf(3, 29, 69)),
        Chatroom("예전 채팅 5", 128, Category.Pizza, 3, "마포대표 근처", 3.9, listOf(3, 29, 69)),
        Chatroom("예전 채팅 6", 128, Category.Korean, 3, "성북구 길음1동 삼부아파트", 3.9, listOf(3, 29, 69)),
        Chatroom("예전 채팅 7", 128, Category.Chinese, 3, "인천 차이나타운", 3.9, listOf(3, 29, 69)),
        Chatroom("예전 채팅 8", 128, Category.Chinese, 3, "인천 차이나타운", 3.9, listOf(3, 29, 69))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "OnCreateView Popout!!!!")
        val retview = super.onCreateView(inflater, container, savedInstanceState)
        val chatAdapter = ChatroomAdapter(requireContext())
        with(binding){
            fragmentChatroomToolbar.inflateMenu(R.menu.menu_chat_room)
            fragmentChatroomRecycle.adapter = chatAdapter
        }
        chatAdapter.replaceData(chatroomList)

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback (
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos: Int = viewHolder.adapterPosition
                val toPos: Int = target.adapterPosition
                chatAdapter.swapData(fromPos, toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                chatAdapter.removeData(viewHolder.layoutPosition)
            }
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val icon: Bitmap
                // actionState가 SWIPE 동작일 때 배경을 빨간색으로 칠하는 작업을 수행하도록 함
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = (itemView.bottom - itemView.top).toFloat()
                    val width = height / 4
                    val paint = Paint()
                    if (dX < 0) {  // 왼쪽으로 스와이프하는지 확인
                        // ViewHolder의 백그라운드에 깔아줄 사각형의 크기와 색상을 지정
                        paint.color = Color.parseColor("#ff0000")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, paint)

                        // 휴지통 아이콘과 표시될 위치를 지정하고 비트맵을 그려줌
                        // 비트맵 이미지는 Image Asset 기능으로 추가하고 drawable 폴더에 위치하도록 함
                        icon = BitmapFactory.decodeResource(activity?.applicationContext?.resources, R.drawable.ic_menu_delete)
                        val iconDst = RectF(itemView.right.toFloat() - 3 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, iconDst, null)

                    }
                }

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.fragmentChatroomRecycle)
        return retview
    }
}