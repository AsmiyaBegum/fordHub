package com.ab.fordhub.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ab.fordhub.dao.VehicleServiceDao
import com.ab.fordhub.model.VehicleServiceDetail

@Database(entities = [VehicleServiceDetail::class], version = 1)
abstract class FordDatabase : RoomDatabase() {
    abstract fun vehicleServiceDao(): VehicleServiceDao

    companion object {
        @Volatile
        private var instance: FordDatabase? = null

        fun getDatabase(context:
                        Context): FordDatabase {
            var tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                tempInstance = instance
                if (tempInstance == null) {
                    tempInstance = Room.databaseBuilder(
                        context.applicationContext,
                        FordDatabase::class.java,
                        "ford.db"
                    ).build()
                    instance = tempInstance
                }
            }

            return tempInstance!!
        }
    }
}