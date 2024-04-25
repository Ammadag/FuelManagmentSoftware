package com.example.fuelmanagmentsoftware.activities.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userTable")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val fuel: String,
    val date: String,
)