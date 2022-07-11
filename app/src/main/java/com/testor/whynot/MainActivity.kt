package com.testor.whynot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.testor.whynot.chat.ChatFragment
import com.testor.whynot.home.HomeFragment
import com.testor.whynot.myPage.MyPageFragment
import com.testor.whynot.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.testor.whynot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        auth = Firebase.auth

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(mBinding.bottomNavigationView, navController)



    }

}

