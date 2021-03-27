package com.theaverageguy.roompractice.repository

import androidx.lifecycle.LiveData
import com.theaverageguy.roompractice.room.modelClasses.PhoneBook
import com.theaverageguy.roompractice.room.database.UserDao


class PhoneBookRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<PhoneBook>> = userDao.readAllData()

    suspend fun addUser(phoneBook: PhoneBook) {
        userDao.addUser(phoneBook)
    }

    suspend fun updateUser(phoneBook: PhoneBook) {
        userDao.updateUser(phoneBook)
    }

    suspend fun deleteUser(phoneBook: PhoneBook) {
        userDao.deleteUser(phoneBook)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

}
