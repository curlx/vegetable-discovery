package com.challenge.vegetablediscovery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.vegetablediscovery.data.dao.VegetableDao
import com.challenge.vegetablediscovery.domain.model.Vegetable

@Database(
    version = 2,
    entities = [Vegetable::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vegetableDao(): VegetableDao

    companion object {

        private const val DATABASE_NAME: String = "vegetable-discovery"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, databaseName: String = DATABASE_NAME): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    databaseName
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
