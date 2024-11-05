package com.lggpmlrg.canitaspadv2_a.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lggpmlrg.canitaspadv2_a.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Update
    suspend fun update(nota: Nota)

    @Delete
    suspend fun delete(nota: Nota)

    @Query("SELECT * from notas WHERE id = :id")
    fun getItem(id: Int): Flow<Nota>

    @Query("SELECT * from notas ORDER BY name ASC")
    fun getAllItems(): Flow<List<Nota>>
}
