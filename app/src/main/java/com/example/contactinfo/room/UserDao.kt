package com.example.contactinfo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user:UserEntity):Long

    @Delete
    suspend fun delete(user:UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("DELETE FROM user")
    suspend fun clearAll()

    @Query("SELECT * FROM user")
    fun selectall():LiveData<List<UserEntity>>
}