package com.ab.fordhub.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ab.fordhub.util.JsonNavType
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class VehicleDetail(
   var name : String = "",
   var description : String = "",
   var specification : String = "",
   var feature : String = "",
   var url : String = ""
){
   override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class VehicleDetailArgType : JsonNavType<VehicleDetail>() {
   override fun fromJsonParse(value: String): VehicleDetail = Gson().fromJson(value, VehicleDetail::class.java)
   override fun VehicleDetail.getJsonParse(): String =  Gson().toJson(this)
}

data class VehicleService(
   var name : String = "",
   var description : String = "",
   var specification : String = "",
   var feature : String = "",
   var url : String = ""
)

@Entity(tableName = "VehicleServiceDetail", indices = [Index(value = ["id"], unique = true)])
data class VehicleServiceDetail(
   @ColumnInfo
   @PrimaryKey(autoGenerate = true) var id: Long = 0,
   var carName : String = "" ,
   var model : String = "" ,
   var serviceType : String = "" ,
   var date : String = "" ,
   var location : String = ""

)

data class ServiceDetails(
@SerializedName("car_name") val carName: String,
@SerializedName("car_model") val carModel : List<String>,
@SerializedName("available_services")val availableServices : List<String>,
val locations : List<String>
)


data class FordCenters(
   val url : String,
   val location : String,
   val workingTime : String,
   val service : String
)

