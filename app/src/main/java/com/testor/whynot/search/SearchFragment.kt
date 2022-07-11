package com.testor.whynot.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.testor.whynot.R
import com.testor.whynot.databinding.FragmentSearchBinding
import com.testor.whynot.model.CounselorModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null
    private val counselorSearchAdapter = CounselorSearchAdapter()
    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: DatabaseReference
    private val counselorList = mutableListOf<CounselorModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentSearchBinding = FragmentSearchBinding.bind(view)
        binding = fragmentSearchBinding

        auth = Firebase.auth

        userDB = Firebase.database.reference.child("Users")
        val currentUserDB = userDB.child(getCurrentUserID())
        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                getUsersDB()
            }
            override fun onCancelled(error: DatabaseError) {}
        })


        fragmentSearchBinding.searchCounselorRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentSearchBinding.searchCounselorRecyclerView.adapter = counselorSearchAdapter
    }

    private fun getCurrentUserID(): String {
        if (auth.currentUser == null) {
            Toast.makeText(requireActivity(), "로그인 실패..", Toast.LENGTH_SHORT).show()
        }

        return auth.currentUser?.uid.orEmpty()
    }

    private fun getUsersDB() {
        userDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                //if (snapshot.child("userId").value != getCurrentUserID()) { // 내 아이디는 안 보여줌
                    var name = "undecided"
                    var hashTag = mutableListOf<String>()
                    if (snapshot.child("name").value != null) {
                        name = snapshot.child("name").value.toString()
                    }

                    counselorList.add(CounselorModel("", name, "", "", "", hashTag, ""))
                    counselorSearchAdapter.submitList(counselorList)
                    counselorSearchAdapter.notifyDataSetChanged()
                //}


            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }



}