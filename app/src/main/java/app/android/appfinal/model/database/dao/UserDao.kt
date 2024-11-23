package app.android.appfinal.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.android.appfinal.model.database.entities.UserEntity

@Dao
interface UserDao {

    //@Query("SELECT * FROM usuarios")
    @Query("SELECT * FROM usuarios ORDER BY nombre DESC")
    suspend fun getAllUsers():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(usuarios:List<UserEntity>)

    @Insert
    suspend fun insertar(usuario: UserEntity)

    @Query("DELETE FROM usuarios")
    suspend fun eliminarTodos()

    @Delete
    suspend fun eliminar(user: UserEntity)

}