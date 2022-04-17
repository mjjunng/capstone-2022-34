package com.capstone.momomeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.momomeal.databinding.ActivityMainBinding
import com.capstone.momomeal.feature.BottomNavigator
import com.capstone.momomeal.feature.MainTab

import com.google.android.material.bottomnavigation.BottomNavigationView

// 기획상으로는 이 화면에서 정렬되어있는 채팅방을 볼 수 있음.

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val mainBnv: BottomNavigationView by lazy {
        findViewById(R.id.bnv_main)
    }
    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fr_main_navi_host)
            ?: throw IllegalStateException("the container MUST contain a fragment at least one")
        navHostFragment.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController.apply {
            navigatorProvider.addNavigator(
                BottomNavigator(
                    R.id.fr_main_navi_host,
                    supportFragmentManager
                )
            )
            // set a graph at code not XML, because add a custom navigator
            setGraph(R.navigation.bottom_navigation)
            mainBnv.setupWithNavController(this)
        }

        savedInstanceState?.getInt(KEY_SELECTED_TAB)
            ?.let {
                MainTab.from(it)
            }
            ?.itemId
            ?.let {
                mainBnv.selectedItemId = it
            }
        // BottomNavigationView의 OnClickListener
//        mainBnv.setOnItemSelectedListener {
//
//
//        }
//        binding.

    }
    companion object {
        private const val KEY_SELECTED_TAB = "selectedTab"
    }

    /*public fun changeFragment(fragment: Fragment) {
        Log.d(TAG, "changeFragment Activated")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_main_full_container, fragment)
            .commit()
    }*/
}
