package com.sumit.bookdatabase.ui.addUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.bookdatabase.data.User
import com.sumit.bookdatabase.other.Messages
import com.sumit.bookdatabase.repository.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PHONE_NUMBER_LIMIT = 10

@HiltViewModel
class AddUserViewModel @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl) :
    ViewModel() {

    fun checkValidation(
        name: String?,
        number: String?,
        typeOfBook: String
    ): String {
        var message = ""
        message = if (name.isNullOrEmpty()) {
            Messages.NAME_NULL
        } else if (number.isNullOrEmpty()) {
            Messages.NUMBER_NULL
        } else if (number.length < PHONE_NUMBER_LIMIT || number.length > PHONE_NUMBER_LIMIT) {
            Messages.INVALID_NUMBER
        } else {
            val user = User(name = name, number = number, typeOfBook = typeOfBook)
            addToDataBase(user)
            Messages.ADDED_TO_DATABASE
        }
        return message
    }

    private fun addToDataBase(user: User) {
        viewModelScope.launch {
            mainRepositoryImpl.addUser(user)
        }
    }
}