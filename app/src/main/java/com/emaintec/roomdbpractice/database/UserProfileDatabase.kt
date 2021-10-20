package com.emaintec.roomdbpractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emaintec.roomdbpractice.data.UserProfile
import com.emaintec.roomdbpractice.util.UserProfileDao


@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
abstract class UserProfileDatabase : RoomDatabase() {

    abstract fun userDao() : UserProfileDao

    companion object{

        @Volatile
        private var instance : UserProfileDatabase? = null

        fun getInstance(context : Context) : UserProfileDatabase? {
            val tempInstance = instance
            if(tempInstance != null) {
                return instance
            }

            synchronized(this){
                val instance2 = Room.databaseBuilder(
                    context.applicationContext,
                    UserProfileDatabase::class.java,
                    "user_database"
                ).build()
                instance = instance2
                return instance2
            }

        }

    }


}


