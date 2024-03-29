package com.jueggs.andutils.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

class OnCreateOnOpenCallback(private val onCreateAction: (SupportSQLiteDatabase) -> Unit, private val onOpenAction: (SupportSQLiteDatabase) -> Unit) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadExecutor().execute { onCreateAction(db) }
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        Executors.newSingleThreadExecutor().execute { onOpenAction(db) }
    }
}