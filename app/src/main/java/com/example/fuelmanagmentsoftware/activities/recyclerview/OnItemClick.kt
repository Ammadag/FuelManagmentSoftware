package com.example.fuelmanagmentsoftware.activities.recyclerview

import com.example.fuelmanagmentsoftware.activities.room.UserInfo

interface OnItemClick {

    fun onUpdate(userinfo: UserInfo)
    fun onDelete(id: Int)
}