package com.example.toyproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.toyproject.data.db.dao.SkinDao
import com.example.toyproject.data.db.entities.Skins
import com.example.toyproject.util.Constants.DATABASE_NAME

@Database(entities = [Skins::class], version = 4, exportSchema = false)
abstract class SkinDatabase : RoomDatabase() {

    abstract fun skinDao(): SkinDao

    companion object {
        @Volatile
        private var instance: SkinDatabase? = null

        fun getDatabase(context: Context): SkinDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, SkinDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}