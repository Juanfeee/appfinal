package app.android.appfinal

import androidx.room.Database
import androidx.room.RoomDatabase
import app.android.appfinal.model.database.dao.UserDao
import app.android.appfinal.model.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UsuarioDatabase: RoomDatabase() {
    abstract fun getUserDao():UserDao
}