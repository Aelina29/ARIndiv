package com.google.ar.core.codelabs.hellogeospatial.helpers

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.google.ar.core.codelabs.hellogeospatial.Target
import kotlinx.coroutines.flow.Flow

@Database(entities = [Target::class], version = 1)
abstract class TargetDatabase : RoomDatabase() {
    abstract fun targetDao(): TargetDao

    companion object {
        private var db: TargetDatabase? = null
        private const val DB_NAME = "targets.db"
        private val Lock = Any()
        fun getInstance(context: Context): TargetDatabase {
            synchronized(Lock) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(context, TargetDatabase::class.java, DB_NAME).build()
                db = instance
                return instance
            }
        }
    }

}

@Dao
interface  TargetDao {
    @Query("SELECT * FROM targets")
    fun getAllTargetsAsFlow(): Flow<List<Target>>
    @Query("SELECT * FROM targets")
    fun getAllTargets(): List<Target>
    @Insert
    fun insert(product: Target)

    @Update
    fun update(product: Target)

    @Delete
    fun delete(product: Target)
}