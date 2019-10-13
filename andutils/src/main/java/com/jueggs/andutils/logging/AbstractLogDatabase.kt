package com.jueggs.andutils.logging

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Add this database for logging in case there is no other room database yet. Derive from this class and add '@Database(entities = [LogEntryEntity::class], version = 1)' annotation
 */
abstract class AbstractLogDatabase : RoomDatabase() {
    abstract fun getLogDao(): ILogDao

    companion object {
        private var INSTANCE: AbstractLogDatabase? = null
        private const val NAME = "logs.db"

        fun getInstance(context: Context): AbstractLogDatabase {
            if (INSTANCE == null) {
                synchronized(AbstractLogDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AbstractLogDatabase::class.java, NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE!!
        }
    }
}