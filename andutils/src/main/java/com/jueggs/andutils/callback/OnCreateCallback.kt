package com.jueggs.andutils.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

class OnCreateCallback(private val onCreateAction: (SupportSQLiteDatabase) -> Unit) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadExecutor().execute { onCreateAction(db) }
    }
}