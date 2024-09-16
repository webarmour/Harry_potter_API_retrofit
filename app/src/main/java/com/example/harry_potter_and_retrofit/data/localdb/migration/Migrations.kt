package com.example.harry_potter_and_retrofit.data.localdb.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_1_2 = object : Migration(1,2){

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE  character ADD COLUMN birthday TEXT")
    }

}