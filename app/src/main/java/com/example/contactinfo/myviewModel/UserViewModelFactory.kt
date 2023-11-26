package com.example.contactinfo.myviewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactinfo.room.UserRepository
import java.lang.IllegalArgumentException

class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewmodel here")
    }
}