package com.example.contactinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactinfo.databinding.ActivityMainBinding
import com.example.contactinfo.myviewModel.UserViewModel
import com.example.contactinfo.myviewModel.UserViewModelFactory

import com.example.contactinfo.room.UserEntity
import com.example.contactinfo.room.UserRepository
import com.example.contactinfo.room.UserRoom
import com.example.contactinfo.viewUi.recyclerviewAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room database
        val dao= UserRoom.getInstance(application).userDao
        val repository= UserRepository(dao)
        val factory= UserViewModelFactory(repository)

        //viewmodel
        viewModel= ViewModelProvider(this,factory).get(UserViewModel::class.java)

        binding.myviewModel=viewModel
        binding.lifecycleOwner=this

       initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager= LinearLayoutManager(this)
        DisplayUserList()
    }
    private fun DisplayUserList() {
        viewModel.users.observe(
            this,
            Observer {
                binding.recyclerview.adapter=recyclerviewAdapter(
                    it ,{ selectedItem: UserEntity -> listItemClicked(selectedItem) })
            })
    }

    private fun listItemClicked(selectedItem: UserEntity) {
        Toast.makeText(this,"selected name is ${selectedItem.name} ",Toast.LENGTH_LONG).show()
        viewModel.initUpdateAndDelete(selectedItem)
    }
}