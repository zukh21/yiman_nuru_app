package com.zim.yiman_nuru

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class TestDBManager(val context: Context) {
    val testDBHelper = TestDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = testDBHelper.writableDatabase
    }

    fun insertToDB(level: Int){
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_LEVEL, level)
        }
        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }
    @SuppressLint("Range")
    fun readDBData(): ArrayList<Int>{
        val dataList = ArrayList<Int>()

        val cursor = db?.query(MyDBNameClass.TABLE_NAME, null, null,
            null, null, null, null)

        with(cursor){
            while (this?.moveToNext()!!){
                val dataInt = cursor?.getInt(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_LEVEL))
                dataList.add(dataInt!!.toInt())
            }
        }

        cursor?.close()

        return dataList
    }
    fun closeDB(){
        testDBHelper.close()
    }
}