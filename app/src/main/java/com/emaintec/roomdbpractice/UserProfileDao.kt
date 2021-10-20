package com.emaintec.roomdbpractice

import androidx.room.*
import com.google.firebase.firestore.auth.User

interface UserProfileDao {


    @Entity
    data class User (
        var name : String,
        var age : String,
        @Embedded
        var userdata : Userdata
        )

    {
        @PrimaryKey(autoGenerate = true)
        var id = 0
    }

    data class Userdata(

        var phone : String,
        var address : String
    )


    @Dao
    interface UserDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(user: User)

        @Update
        suspend fun update(user: User)

        @Delete
        suspend fun delete(user: User)

        @Query("SELECT * FROM User")
        suspend fun getAll(): List<User>

        @Query("DELETE FROM User ")
        suspend fun deleteAll()

    }
}