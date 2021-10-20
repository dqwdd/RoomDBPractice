package com.emaintec.roomdbpractice.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.emaintec.roomdbpractice.UserRepository
import com.emaintec.roomdbpractice.data.UserProfile
import com.emaintec.roomdbpractice.database.UserProfileDatabase
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<UserProfile>>
    private val repository: UserRepository

    init {
        val userDao = UserProfileDatabase.getDatabase(application)?.userDao()
        repository = userDao?.let { UserRepository(it) }!!
        readAllData = repository.readAllData
    }

    fun addUser(user: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }



}