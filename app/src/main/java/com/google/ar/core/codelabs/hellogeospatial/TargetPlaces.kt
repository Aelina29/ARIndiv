package com.google.ar.core.codelabs.hellogeospatial

import android.provider.CallLog.Locations
import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

object TargetPlaces {
    var targets:ArrayList<Target>  = arrayListOf(
//        Target("Пить кофе",47.218590, 39.710854,false),
//        Target("Кекс",47.219722, 39.710874,false),
//        Target("Балик",47.220475, 39.710698,false),
    )
}
@Entity(tableName = "targets")
data class Target(
    @ColumnInfo(name = "name") val name: String,
//    @ColumnInfo(name = "location") val location: LatLng,
    @ColumnInfo(name = "locationLat") val locationLat: Double,
    @ColumnInfo(name = "locationLng") val locationLng: Double,
    @ColumnInfo(name = "isReached") var isReached: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)