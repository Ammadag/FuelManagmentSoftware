package com.example.fuelmanagmentsoftware.activities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagmentsoftware.activities.room.UserDB
import com.example.fuelmanagmentsoftware.activities.room.UserInfo
import kotlinx.coroutines.launch


class TodoViewModel : ViewModel() {
    private val TodoDb: UserDB? = UserDB._INSTANCE



    fun insertData(userinfo: UserInfo) {
        viewModelScope.launch {
            TodoDb!!.userDao().insertTable(userinfo)
        }
    }

    fun getData(): LiveData<List<UserInfo?>> {
        return TodoDb!!.userDao().getAllItem()
    }

    fun updateData( userinfo: UserInfo) {
        viewModelScope.launch {
            TodoDb!!.userDao().updateTable(userinfo)
        }
    }

    fun deleteItem(id: Int){
        viewModelScope.launch {
            TodoDb!!.userDao().deleteById(id)
        }
    }
}
