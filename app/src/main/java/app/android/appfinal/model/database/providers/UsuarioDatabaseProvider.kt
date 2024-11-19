package app.android.appfinal.model.database.providers

import android.content.Context
import androidx.room.Room
import app.android.appfinal.UsuarioDatabase

object UsuarioDatabaseProvider {
    fun getDatabase(context: Context): UsuarioDatabase {
        return Room.databaseBuilder(
            context,
            UsuarioDatabase::class.java,
            "app_database"
        ).build()
    }
}