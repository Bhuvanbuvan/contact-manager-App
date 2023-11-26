package com.example.contactinfo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserRoom :RoomDatabase(){
    abstract val userDao:UserDao


    //singleTon
    companion object{
        @Volatile
        private var INSTANCE:UserRoom?=null
        fun getInstance(context: Context):UserRoom{
            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        UserRoom::class.java,
                        "user_db").build()
                }
                INSTANCE=instance
                return instance
            }
        }
    }

}