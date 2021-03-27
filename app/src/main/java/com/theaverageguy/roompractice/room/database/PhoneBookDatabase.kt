package com.theaverageguy.roompractice.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.theaverageguy.roompractice.room.modelClasses.PhoneBook

@Database(entities = [PhoneBook::class], version = 1, exportSchema = false)
abstract class PhoneBookDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: PhoneBookDatabase? = null

        fun getDatabase(context: Context): PhoneBookDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneBookDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}