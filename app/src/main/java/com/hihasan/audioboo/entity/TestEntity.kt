package com.hihasan.audioboo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_test")
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)