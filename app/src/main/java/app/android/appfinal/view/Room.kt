package app.android.appfinal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import app.android.appfinal.UsuarioAdapter


import app.android.appfinal.R

import app.android.appfinal.databinding.FragmentRoomBinding
import app.android.appfinal.model.database.dao.UserDao
import app.android.appfinal.model.database.providers.UsuarioDatabaseProvider
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
        val view = binding.root

        val db = UsuarioDatabaseProvider.getDatabase(requireContext())
        val usuarioDao = db.getUserDao()

        // Inicializa el adaptador de usuarios y lo asigna al ListView
        usuarioAdapter = UsuarioAdapter(requireContext(), mutableListOf())
        binding.lvUsers.adapter = usuarioAdapter

        // Cargar los usuarios desde la base de datos
        cargarUsuarios()

        // Configura el botón para eliminar todos los usuarios
        val btnEliminarTodos = view.findViewById<Button>(R.id.btnEliminarTodos)
        btnEliminarTodos.setOnClickListener {
            eliminarTodos(usuarioDao)
        }

        return view
    }

    // Método para cargar usuarios en el ListView
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

    // Método para eliminar todos los usuarios de la base de datos
    private fun eliminarTodos(usuarioDao: UserDao) {
        lifecycleScope.launch(Dispatchers.IO) {
            usuarioDao.eliminarTodos()

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Todos los usuarios han sido eliminados", Toast.LENGTH_SHORT).show()
                cargarUsuarios() // Recargar la lista de usuarios
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
