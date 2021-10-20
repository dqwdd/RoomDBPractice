package com.emaintec.roomdbpractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.auth.User

class RoomDataBase {


    @Database(entities = [User::class], version = 1)
    abstract class UserDatabase : RoomDatabase() {
        abstract fun userDao() : UserDao

        companion object{
            private var instance : UserDatabase? = null

            @Synchronized
            fun getInstance(context : Context) : UserDatabase? {
                if(instance == null){
                    synchronized(UserDatabase::class){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            "user.db"
                        ).build()
                    }
                }
                return instance
            }
        }
    }


}