package app.android.appfinal.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import app.android.appfinal.CameraActivity
import app.android.appfinal.R
import app.android.appfinal.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_first, container, false)

        val btnGrabadora=root.findViewById<Button>(R.id.btnGrabadora)

        val btnCamara=root.findViewById<Button>(R.id.btnCamara)
        btnGrabadora.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_room)
        }
        btnCamara.setOnClickListener {
            // Iniciar la nueva actividad
            val intent = Intent(activity, CameraActivity::class.java) // Asegúrate de que la actividad esté correctamente importada
            startActivity(intent)
        }
        return root
    }


}