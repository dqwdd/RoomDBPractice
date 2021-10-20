package com.emaintec.roomdbpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.*
import com.emaintec.roomdbpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        @Entity
        data class User (
            var name : String,
            var age : String,
            @Embedded
            var userdata : UserData
        ) {
            @PrimaryKey(autoGenerate = true)
            var id = 0
        }






    }



}