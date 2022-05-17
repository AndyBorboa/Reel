package borboa.andrea.reel_proyectomoviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalificacionesFragment : Fragment() {

    private lateinit var ComentariosRecyclerview: RecyclerView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        val usuario = getArguments()?.getString("usuario")
        val idPeli = getArguments()?.getString("idPeli")
        val comentarios = getArguments()?.getSerializable("comentarios") as ArrayList<comentario>

        val view = inflater.inflate(R.layout.fragment_calificaciones, container, false)
        val btn_comentario = view.findViewById<View>(R.id.btn_comentario) as Button

        btn_comentario.setOnClickListener {
            val intent = Intent(activity, ActivityComentario::class.java)
            val bundle = Bundle()
            bundle.putString("usuario", usuario)
            bundle.putString("idPeli",idPeli)
            bundle.putSerializable("comentarios",comentarios)
            intent.putExtras(bundle);
            activity?.startActivity(intent)
        }


        ComentariosRecyclerview = view.findViewById(R.id.ComentariosRecyclerView)
        ComentariosRecyclerview.setHasFixedSize(true)
        ComentariosRecyclerview.setLayoutManager(LinearLayoutManager(requireActivity().applicationContext, RecyclerView.VERTICAL, false))

        val comentariosAdapter = ComentariosAdapter(comentarios)
        ComentariosRecyclerview.setAdapter(comentariosAdapter)




        return view
    }



}