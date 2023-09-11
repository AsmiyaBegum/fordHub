package com.ab.fordhub.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ab.fordhub.model.VehicleServiceDetail

@Dao
interface VehicleServiceDao {
    @Insert
    fun insertService(draft: VehicleServiceDetail): Long

    @Query("SELECT * FROM vehicleservicedetail ORDER BY id desc")
    fun getAllServices(): List<VehicleServiceDetail>

    @Delete
    fun cancelService(service : VehicleServiceDetail)
}