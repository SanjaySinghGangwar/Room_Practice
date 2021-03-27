package com.theaverageguy.roompractice.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.theaverageguy.roompractice.room.modelClasses.PhoneBook
import com.theaverageguy.roompractice.repository.PhoneBookRepository
import com.theaverageguy.roompractice.room.database.PhoneBookDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class myViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<PhoneBook>>
    private val repository: PhoneBookRepository

    init {
        val userDao = PhoneBookDatabase.getDatabase(
            application
        ).userDao()
        repository = PhoneBookRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(phoneBook: PhoneBook){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(phoneBook)
        }
    }

    fun updateUser(phoneBook: PhoneBook){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(phoneBook)
        }
    }

    fun deleteUser(phoneBook: PhoneBook){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(phoneBook)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

}