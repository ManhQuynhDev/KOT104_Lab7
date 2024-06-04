package com.quynhlm.dev.lab7.Models

import androidx.room.ColumnInfo
import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class Movie (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title")
    val title : String ,
    @ColumnInfo(name = "time")
    var time : String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl : String ,
    @ColumnInfo(name = "describe")
    val describe : String ,
    @ColumnInfo(name = "startUp")
    val startUp : String
)