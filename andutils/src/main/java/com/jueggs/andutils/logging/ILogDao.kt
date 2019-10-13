package com.jueggs.andutils.logging

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Add this dao interface to an existing room database together with using RoomLogTarget for logging. Add LogEntryEntity to the database entity definition
 */
@Dao
interface ILogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: LogEntryEntity)
}