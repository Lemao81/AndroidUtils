package com.jueggs.andutils.callback

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import java.util.concurrent.Executors

class OnOpenCallback(private val onOpenAction: (SupportSQLiteDatabase) -> Unit) : RoomDatabase.Callback() {
    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        Executors.newSingleThreadExecutor().execute { onOpenAction(db) }
    }
}