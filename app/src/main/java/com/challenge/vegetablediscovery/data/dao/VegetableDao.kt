package com.challenge.vegetablediscovery.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.vegetablediscovery.domain.model.Vegetable
import kotlinx.coroutines.flow.Flow

@Dao
interface VegetableDao {

    @Query("SELECT * FROM vegetables ORDER BY id")
    fun getVegetables(): Flow<List<Vegetable>>

    @Query("SELECT * FROM vegetables WHERE id = :vegetableId")
    fun getVegetable(vegetableId: Long): Flow<Vegetable?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vegetables: List<Vegetable>)
}