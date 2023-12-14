package com.google.ar.core.codelabs.hellogeospatial

import android.content.Context
import android.provider.CallLog.Locations
import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.ar.core.codelabs.hellogeospatial.helpers.TargetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object TargetPlaces {
    var targets:ArrayList<Target>  = arrayListOf(
//        Target("Пить кофе",47.218590, 39.710854,false),
//        Target("Кекс",47.219722, 39.710874,false),
//        Target("Балик",47.220475, 39.710698,false),
    )
    fun onDelete(context: Context, t: Target) {
        GlobalScope.launch(Dispatchers.IO) {
            TargetDatabase.getInstance(context).targetDao().delete(t)
        }
    }

    fun onAdd(context: Context, t: Target) {
        GlobalScope.launch(Dispatchers.IO) {
            TargetDatabase.getInstance(context).targetDao().insert(t)
        }
    }

    fun onUpdate(context: Context, t: Target, f: Boolean) {
        val newTarget = t.copy(isReached = f)
        GlobalScope.launch(Dispatchers.IO) {
            TargetDatabase.getInstance(context).targetDao().update(newTarget)
        }
    }
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