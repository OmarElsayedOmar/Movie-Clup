package com.helpel

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ArticalDbHleper(context: Context): SQLiteOpenHelper (context,DBName,null,DBversion) {

    override fun onCreate(database: SQLiteDatabase?) {
val sql= "create table ${DB.Table_Name} (" +
        "${DB.ID}  INTEGER PRIMARY KEY ,"+
        "${DB.DB_Title} Text,"+
        "${DB.body} Text" +
        ")"
  database?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    companion object{
        private const val DBName="ArticalDataBase"
        private const val DBversion= 1

    }
}