package com.testor.whynot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.testor.whynot.chat.ChatFragment
import com.testor.whynot.home.HomeFragment
import com.testor.whynot.myPage.MyPageFragment
import com.testor.whynot.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragmentContainerView)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        changeFragment(HomeFragment())

        auth = Firebase.auth


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


    fun changeFragment(newFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, newFragment)
            .commit()
    }



}

