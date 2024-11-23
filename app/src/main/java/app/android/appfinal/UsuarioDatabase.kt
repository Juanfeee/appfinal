package app.android.appfinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.android.appfinal.model.database.dao.UserDao
import app.android.appfinal.model.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UsuarioDatabase : RoomDatabase() {

    // Método abstracto para obtener el DAO
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UsuarioDatabase? = null

        // Método para obtener la instancia única de la base de datos
        fun getInstance(context: Context): UsuarioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioDatabase::class.java,
                    "usuario_database"
                )
                    .fallbackToDestructiveMigration() // Esto borra los datos si cambias la versión. Quita esta línea si planeas usar migraciones.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
