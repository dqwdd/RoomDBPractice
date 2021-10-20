package com.emaintec.roomdbpractice.util

import androidx.lifecycle.LiveData
import androidx.room.*
import com.emaintec.roomdbpractice.data.UserProfile

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserProfile)

//    @Update
//    suspend fun update(user: UserProfile)
//
//    @Delete
//    suspend fun delete(user: UserProfile)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserProfile>>

//    @Query("DELETE FROM user_table ")
//    fun deleteAll()

}