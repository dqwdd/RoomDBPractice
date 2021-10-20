package com.emaintec.roomdbpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.*
import com.emaintec.roomdbpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var db : UserDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        db = UserDatabase.getInstance(applicationContext)!!
        fetchUserList()
    }

    fun fetchUserList(){
        var userListText = "사용자 목록"

        CoroutineScope(Dispatchers.Main).launch {

            val load = async(Dispatchers.IO) {
                val userList = db.userDao().getAll()
                for(i in userList){
                    userListText += "\n${i.id} ${i.name}, ${i.age}"
                }
            }
            load.await()
            binding.textView.text = userListText
        }
    }

    fun addUser(view : View){
        val user = User(binding.nameEditView.text.toString(),binding.ageEditView.text.toString())

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