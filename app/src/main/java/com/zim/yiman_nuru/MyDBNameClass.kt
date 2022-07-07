package com.zim.yiman_nuru

import android.provider.BaseColumns

object MyDBNameClass: BaseColumns {
    const val TABLE_NAME = "levels"
    const val COLUMN_NAME_LEVEL = "level"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "testLevels.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_LEVEL INTEGER)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}