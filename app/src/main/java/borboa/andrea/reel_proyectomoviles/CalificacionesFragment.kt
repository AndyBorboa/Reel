package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CalificacionesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calificaciones, container, false)

        val btn_comentario = view.findViewById<View>(R.id.btn_comentario) as Button

        btn_comentario.setOnClickListener{
            val intent = Intent (activity , ActivityComentario::class.java)
            activity?.startActivity(intent)
        }

        return view
    }

}