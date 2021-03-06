package com.emaintec.roomdbpractice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val age : Int
) {}