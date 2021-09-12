package com.example.sqliteapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class MyDBManager(val context: Context) {
    val myDBHelper=MyDBHelper(context)

    var db:SQLiteDatabase? = null

    fun openDB() {
        db = myDBHelper.writableDatabase
    }

    fun insertToDB(title: String, content: String) {
        val values = ContentValues().apply {
            put(DBNameClass.COLUMN_NAME_TITLE, title)
            put(DBNameClass.COLUMN_NAME_CONTENT, content)
            Log.d("sql", "title $title")
        }

        db?.insert(DBNameClass.TABLE_NAME, null, values)
    }

    fun readDBData(): ArrayList<String> {
        val dataList = ArrayList<String>()

        val cursor = db?.query(DBNameClass.TABLE_NAME, null, null, null, null, null, null)

        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataText = this.getString(this.getColumnIndex(DBNameClass.COLUMN_NAME_TITLE))
                Log.d("sql", dataText.toString())
                dataList.add(dataText)
            }
        }

        cursor?.close()

        return dataList
    }

    fun closeDB() {
        myDBHelper.close()
    }

}