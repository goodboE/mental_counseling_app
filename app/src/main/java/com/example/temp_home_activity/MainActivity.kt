package com.example.temp_home_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.temp_home_activity.chat.ChatFragment
import com.example.temp_home_activity.home.HomeFragment
import com.example.temp_home_activity.myPage.MyPageFragment
import com.example.temp_home_activity.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val topFrameLayout: FrameLayout by lazy {
        findViewById(R.id.topFrameLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        changeFragment(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.home -> changeFragment(HomeFragment())
                R.id.search -> changeFragment(SearchFragment())
                R.id.chat -> changeFragment(ChatFragment())
                R.id.myPage -> changeFragment(MyPageFragment())
            }
            true
        }


    }

    private fun changeFragment(newFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.topFrameLayout, newFragment)
            .commit()
    }

}

