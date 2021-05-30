package com.challenge.vegetablediscovery.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.vegetablediscovery.data.entities.VegetableEntity
import com.challenge.vegetablediscovery.domain.model.Vegetable
import com.challenge.vegetablediscovery.domain.model.VegetableDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface VegetableDao {

    @Query("SELECT id, name, imageUrl, mainVitamin FROM vegetables ORDER BY id")
    fun getVegetables(): Flow<List<Vegetable>>

    @Query("SELECT id, name, description, imageUrl, mainVitamin FROM vegetables WHERE id = :vegetableId")
    fun getVegetable(vegetableId: Long): Flow<VegetableDetail?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vegetables: List<VegetableEntity>)
}