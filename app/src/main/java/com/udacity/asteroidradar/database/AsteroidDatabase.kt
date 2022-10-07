package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [AsteroidEntity::class])

abstract class AsteroidDatabase : RoomDatabase(){

    abstract val dao : AsteroidDao


  companion object {
       @Volatile
       private lateinit var instance: AsteroidDatabase

       fun getInstance(context: Context): AsteroidDatabase{
           synchronized(AsteroidDatabase::class.java){
               if(!::instance.isInitialized) {
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       AsteroidDatabase::class.java,
                       "asteroidDatabase").build()
               }
           }
           return instance
       }
   }
}