package com.jueggs.andutils.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg element: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg element: T)

    @Delete
    fun delete(vararg element: T)
}