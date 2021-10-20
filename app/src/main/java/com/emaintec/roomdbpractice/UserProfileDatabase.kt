package com.emaintec.roomdbpractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.auth.User

class UserProfileDatabase {


    @Database(entities = [User::class], version = 1)
    abstract class UserProfileDatabase : RoomDatabase() {
        abstract fun userDao() : UserProfileDao

        companion object{
            private var instance : UserProfileDatabase? = null

            @Synchronized
            fun getInstance(context : Context) : UserProfileDatabase? {
                if(instance == null){
                    synchronized(UserProfileDatabase::class){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserProfileDatabase::class.java,
                            "user.db"
                        ).build()
                    }
                }
                return instance
            }
        }
    }


}