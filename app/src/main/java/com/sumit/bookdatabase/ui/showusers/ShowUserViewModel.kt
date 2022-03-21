package com.sumit.bookdatabase.ui.showusers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.bookdatabase.data.User
import com.sumit.bookdatabase.repository.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowUserViewModel @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl) :
    ViewModel() {

    val cartLiveData: MutableLiveData<List<User>> = MutableLiveData()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = mainRepositoryImpl.getUsers()
            cartLiveData.postValue(users)
        }

    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            mainRepositoryImpl.deleteUser(user)
        }
    }

}