package com.emaintec.roomdbpractice.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.emaintec.roomdbpractice.UserRepository
import com.emaintec.roomdbpractice.data.UserProfile
import com.emaintec.roomdbpractice.database.UserProfileDatabase

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<UserProfile>>
    private val repository: UserRepository

    init {
        val userDao = UserProfileDatabase.getDatabase(application)?.userDao()
        repository = userDao?.let { UserRepository(it) }!!
        readAllData = repository.readAllData
    }

    fun addUser(user: UserProfile) {
        v
    }



}