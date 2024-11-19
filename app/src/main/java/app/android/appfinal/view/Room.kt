package app.android.appfinal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import app.android.appfinal.R

import app.android.appfinal.databinding.FragmentRoomBinding
import app.android.appfinal.model.database.dao.UserDao
import app.android.appfinal.model.database.entities.UserEntity
import app.android.appfinal.model.database.providers.UsuarioDatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Room : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private lateinit var usuarioAdapter: UsuarioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = UsuarioDatabaseProvider.getDatabase(requireContext())
        val usuarioDao = db.getUserDao()

        // Eliminar usuarios si es necesario
        // eliminarUsuarios(usuarioDao)

        // Obtener usuarios y mostrarlos
        obtenerUsuarios(usuarioDao)

        // Crear lista de usuarios de ejemplo y guardarlos en la base de datos
        val listaUsers = listOf(
            UserEntity(nombre = "Luis", apellido = "Perez"),
            UserEntity(nombre = "Maria", apellido = "Rodriguez"),
            UserEntity(nombre = "Margot", apellido = "Perales"),
        )

        CoroutineScope(Dispatchers.IO).launch {
            usuarioDao.insertAllUsers(listaUsers)
        }

        // Inicializar el adaptador con una lista vacía por ahora
        usuarioAdapter = UsuarioAdapter(requireContext(), mutableListOf())
        binding.lvUsers.adapter = usuarioAdapter

        // Cargar los usuarios desde la base de datos
        cargarUsuarios()
    }

    private fun obtenerUsuarios(usuarioDao: UserDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            val usuarios = usuarioDao.getAllUsers()
            withContext(Dispatchers.Main) {
                usuarios.forEach { usuario ->
                    println("Usuario: ${usuario.nombre}, Apellido: ${usuario.apellido}")
                }
            }
        }
    }

    private fun cargarUsuarios() {
        val usuarioDao = UsuarioDatabaseProvider.getDatabase(requireContext()).getUserDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val usuarios = usuarioDao.getAllUsers()
            withContext(Dispatchers.Main) {
                usuarioAdapter.clear()
                usuarioAdapter.addAll(usuarios)
                usuarioAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun eliminarUsuarios(usuarioDao: UserDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            usuarioDao.eliminarTodos()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
