package com.emaintec.roomdbpractice.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emaintec.roomdbpractice.R
import com.emaintec.roomdbpractice.ViewModel.UserViewModel
import com.emaintec.roomdbpractice.data.UserProfile
import com.emaintec.roomdbpractice.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel

    lateinit var binding : FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = binding.edtName.text.toString()
        val age = binding.edtAge.text.toString()

        if (inputCheck(name, age)){
            //Create User Object
            val user = UserProfile(0, name, Integer.parseInt(age.toString()))
            //Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(name: String, age: String): Boolean {
        return !(TextUtils.isEmpty(name) && age.isEmpty())
    }


}