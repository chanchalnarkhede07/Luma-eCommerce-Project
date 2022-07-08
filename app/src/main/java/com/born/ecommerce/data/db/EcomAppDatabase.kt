package com.born.ecommerce.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class EcomAppDatabase : RoomDatabase() {
    // Dao


    companion object {
        private const val DATABASE_NAME = "EcomDatabase"

        fun buildDatabase(context: Context): EcomAppDatabase {
            return Room.databaseBuilder(context, EcomAppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
