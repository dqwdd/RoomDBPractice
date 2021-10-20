package com.emaintec.roomdbpractice

import androidx.lifecycle.LiveData
import com.emaintec.roomdbpractice.data.UserProfile
import com.emaintec.roomdbpractice.util.UserProfileDao

class UserRepository(private val userProfileDao: UserProfileDao) {

    val readAllData : LiveData<List<UserProfile>> = userProfileDao.readAllData()

    suspend fun addUser(user: UserProfile) {
        userProfileDao.addUser(user)
    }

}