package app.android.appfinal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
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
        val btnFormulario=root.findViewById<Button>(R.id.btnFormulario)

        val btnRoom=root.findViewById<Button>(R.id.btnRoom)
        btnGrabadora.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_grabadoraFragment)
        }
        btnRoom.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_room)
        }
        btnFormulario.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_formulario)
        }

        return root
    }


}