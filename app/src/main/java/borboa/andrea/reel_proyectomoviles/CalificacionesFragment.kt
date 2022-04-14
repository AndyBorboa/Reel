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
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class CalificacionesFragment : Fragment() {

    val comentarios = getArguments()?.getSerializable("comentarios") as ArrayList<comentario>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{

        val view = inflater.inflate(R.layout.fragment_calificaciones, container, false)
        val btn_comentario = view.findViewById<View>(R.id.btn_comentario) as Button

        btn_comentario.setOnClickListener {
            val intent = Intent(activity, ActivityComentario::class.java)
            activity?.startActivity(intent)
        }

        var listview: ListView = view.findViewById(R.id.listComentarios) as ListView
        var adaptador: AdaptadorComentarios = AdaptadorComentarios(requireActivity().applicationContext, comentarios)
        listview.adapter = adaptador

        return view
    }

    private class AdaptadorComentarios: BaseAdapter {
        var comentarios = ArrayList<comentario>()
        var contexto: Context?= null

        constructor(contexto: Context, comentarios: ArrayList<comentario>){
            this.comentarios = comentarios
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return comentarios.size
        }

        override fun getItem(p0: Int): Any {
            return comentarios[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var comentario = comentarios[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista= inflador.inflate(R.layout.item_comentario, null)

            var nombreUsuario = vista.findViewById(R.id.nombreusuario) as TextView
            var fecha = vista.findViewById(R.id.fecha) as TextView
            var coments = vista.findViewById(R.id.comentariousuario) as TextView

            nombreUsuario.setText(comentario.nombreUsuario)
            fecha.setText(comentario.fecha)
            coments.setText(comentario.comentario)
            return vista
        }
    }



}