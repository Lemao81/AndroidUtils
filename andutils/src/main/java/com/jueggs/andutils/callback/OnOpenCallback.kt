package com.jueggs.andutils.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

class OnOpenCallback(private val onOpenAction: (SupportSQLiteDatabase) -> Unit) : RoomDatabase.Callback() {
    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        Executors.newSingleThreadExecutor().execute { onOpenAction(db) }
    }
}