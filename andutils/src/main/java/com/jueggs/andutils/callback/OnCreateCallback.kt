package com.jueggs.andutils.callback

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import java.util.concurrent.Executors

class OnCreateCallback(private val onCreateAction: (SupportSQLiteDatabase) -> Unit) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadExecutor().execute { onCreateAction(db) }
    }
}