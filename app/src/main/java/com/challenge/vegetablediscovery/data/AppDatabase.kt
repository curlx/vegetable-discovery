package com.challenge.vegetablediscovery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.domain.model.Vegetable

@Database(entities = [Vegetable::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vegetableDao(): VegetableDao

    companion object {

        private const val DATABASE_NAME: String = "vegetable-discovery"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
