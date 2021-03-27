package com.theaverageguy.roompractice.room.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.theaverageguy.roompractice.room.modelClasses.PhoneBook

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(phoneBook: PhoneBook)

    @Update
    suspend fun updateUser(phoneBook: PhoneBook)

    @Delete
    suspend fun deleteUser(phoneBook: PhoneBook)

    @Query("DELETE FROM phone_book")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM phone_book ORDER BY id ASC")
    fun readAllData(): LiveData<List<PhoneBook>>

}