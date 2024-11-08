package com.lggpmlrg.canitaspadv2_a.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lggpmlrg.canitaspadv2_a.model.Tarea
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tarea: Tarea)

    @Update
    suspend fun update(tarea: Tarea)

    @Delete
    suspend fun delete(tarea: Tarea)

    @Query("SELECT * from tareas WHERE id = :id")
    fun getItem(id: Int): Flow<Tarea>

    @Query("SELECT * from tareas ORDER BY name ASC")
    fun getAllItems(): Flow<List<Tarea>>
}
