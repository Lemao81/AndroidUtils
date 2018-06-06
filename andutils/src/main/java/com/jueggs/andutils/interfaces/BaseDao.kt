package com.jueggs.andutils.interfaces

import android.arch.persistence.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg element: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg element: T)

    @Delete
    fun delete(vararg element: T)
}