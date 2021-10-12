package ar.edu.unlam.structureexample.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RobotDao {

    @Query("SELECT * FROM robots")
    fun getAll(): List<RobotEntity>

    @Query("SELECT * FROM robots WHERE id = :id")
    fun getById(id: Long): List<RobotEntity>

    @Delete
    fun delete(robotEntity: RobotEntity)

    @Insert
    fun insert(vararg robotEntity: RobotEntity)

    @Query("INSERT INTO robots (name) VALUES ('2b, 9s, r2d2')")
    fun populate()
}