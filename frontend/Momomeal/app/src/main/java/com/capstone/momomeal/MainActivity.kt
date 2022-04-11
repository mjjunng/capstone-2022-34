package com.capstone.momomeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.capstone.momomeal.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

// 기획상으로는 이 화면에서 정렬되어있는 채팅방을 볼 수 있음.

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val mainBnv: BottomNavigationView by lazy {
        findViewById(R.id.bnv_main)
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bottomNavigationView : BottomNavigationView = findViewById(R.id.bnv_main)
        var navController = findNavController(R.id.fr_main_navi_host)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when (destination.id){
                R.id.itemHome  -> bottomNavigationView.visibility = View.VISIBLE
                R.id.itemChatRoom -> bottomNavigationView.visibility = View.VISIBLE
                R.id.itemMypage -> bottomNavigationView.visibility = View.VISIBLE
                else ->  bottomNavigationView.visibility = View.GONE
            }
        }
        // BottomNavigationView의 OnClickListener
//        mainBnv.setOnItemSelectedListener {
//
//
//        }
//        binding.

    }

    public fun changeFragment(fragment: Fragment) {
        Log.d(TAG, "changeFragment Activated")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_main_full_container, fragment)
            .commit()
    }
}