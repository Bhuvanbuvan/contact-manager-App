package com.example.contactinfo.room

import androidx.room.Dao

class UserRepository(private val dao: UserDao) {
    val users=dao.selectall()

    suspend fun insert(user:UserEntity):Long{
        return dao.insert(user)
    }

    suspend fun delete(user: UserEntity){
        return dao.delete(user)
    }

    suspend fun update(user: UserEntity){
        return dao.update(user)
    }

    suspend fun deleteall(){
        return dao.clearAll()
    }


}