package com.example.contactinfo.myviewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactinfo.room.UserEntity
import com.example.contactinfo.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel(),Observable {

    //all the table data
    var users=repository.users

    private var isUpdateOrDelete=false
    lateinit var userUpdateOrDelete:UserEntity

    //input name
    @Bindable
    val inputName=MutableLiveData<String?>()

    //input email
    @Bindable
    val inputEmail=MutableLiveData<String?>()

    //button 1 text
    @Bindable
    val saveOrUpdateButtonText=MutableLiveData<String>()

    //button 2 text
    @Bindable
    val clearallOrDeleteButtonText=MutableLiveData<String>()

    //initializer value of the button1 and button2
    init {
        saveOrUpdateButtonText.value="save"
        clearallOrDeleteButtonText.value="clear All"
    }


    //choose the option for save or update method
    fun saveOrUpdate(){
        if (isUpdateOrDelete){
            //working for update
            userUpdateOrDelete.name=inputName.value!!
            userUpdateOrDelete.email=inputEmail.value!!
            update(userUpdateOrDelete)
        }
        else{
            //working for save
            val name=inputName.value!!
            val email=inputEmail.value!!
            insert(UserEntity(0,name,email))
        }
    }

    //choose the option for clearall or delete method

    fun clearAllORDelete(){
        if (isUpdateOrDelete){
            //working for delete
            delete(userUpdateOrDelete)
        }
        else{
            //working for clearall
            clearall()
        }
    }

    private fun insert(userEntity: UserEntity)=viewModelScope.launch{
        repository.insert(userEntity)
    }

    private fun update(userUpdateOrDelete: UserEntity)=viewModelScope.launch {
        repository.update(userUpdateOrDelete)

        //reseting the buuton feilds
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveOrUpdateButtonText.value="save"
        clearallOrDeleteButtonText.value="Clear All"
    }


    private fun delete(userUpdateOrDelete: UserEntity)=viewModelScope.launch {
        repository.delete(userUpdateOrDelete)

        //reseting the buuton feilds
        inputName.value=null
        inputEmail.value=null
        isUpdateOrDelete=false
        saveOrUpdateButtonText.value="save"
        clearallOrDeleteButtonText.value="Clear All"

    }


    private fun clearall()=viewModelScope.launch {
        repository.deleteall()

    }

    fun initUpdateAndDelete(userEntity: UserEntity){
        inputName.value=userEntity.name
        inputEmail.value=userEntity.email
        isUpdateOrDelete=true
        userUpdateOrDelete=userEntity
        saveOrUpdateButtonText.value="Update"
        clearallOrDeleteButtonText.value="Delete"
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}