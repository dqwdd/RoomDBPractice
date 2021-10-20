package com.emaintec.roomdbpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.room.*
import com.emaintec.roomdbpractice.database.UserProfileDatabase
import com.emaintec.roomdbpractice.databinding.ActivityMainBinding
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var db : UserProfileDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        db = UserProfileDatabase.getDatabase(applicationContext)!!
        fetchUserList()
    }

    fun fetchUserList(){
        var userListText = "사용자 목록"

        CoroutineScope(Dispatchers.Main).launch {

            val load = async(Dispatchers.IO) {
                val userList = db.userDao().readAllData()
                for(i in userList){
                    userListText += "\n${i.id} ${i.name}, ${i.age}"
                }
            }
            load.await()
            binding.textView.text = userListText
        }
    }

    fun addUser(view : View){
        val user = User(binding.textView.text.toString(),binding.tex.text.toString())

        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().insert(user)
        }
        fetchUserList()
    }

    fun deleteAllUser(view : View){
        CoroutineScope(Dispatchers.Main).launch {
            val delete = async(Dispatchers.IO) {
                db.userDao().deleteAll()
            }
            delete.await()
            fetchUserList()
        }
    }
}






    }



}