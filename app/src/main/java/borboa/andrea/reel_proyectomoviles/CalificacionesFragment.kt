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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import com.google.firebase.database.*

class CalificacionesFragment : Fragment() {

    private lateinit var ComentariosRecyclerview: RecyclerView
    private lateinit var bdref: DatabaseReference
    private lateinit var comentariosArrayList: ArrayList<comentario>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{

        val comentarios = getArguments()?.getSerializable("comentarios") as ArrayList<comentario>

        val view = inflater.inflate(R.layout.fragment_calificaciones, container, false)
        val btn_comentario = view.findViewById<View>(R.id.btn_comentario) as Button

        btn_comentario.setOnClickListener {
            val intent = Intent(activity, ActivityComentario::class.java)
            activity?.startActivity(intent)
        }

        //PromosRecyclerView
        ComentariosRecyclerview = view.findViewById(R.id.ComentariosRecyclerView)
        ComentariosRecyclerview.setHasFixedSize(true)
        ComentariosRecyclerview.setLayoutManager(LinearLayoutManager(requireActivity().applicationContext, RecyclerView.VERTICAL, false))

        val comentariosAdapter = ComentariosAdapter(comentarios)
        ComentariosRecyclerview.setAdapter(comentariosAdapter)
        comentariosArrayList= arrayListOf<comentario>()
        getComentarioData()

        return view
    }

    private fun getComentarioData(){
        bdref= FirebaseDatabase.getInstance().getReference("comentarios")
        bdref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(comentarioSnapshot in snapshot.children){
                        val estreno = comentarioSnapshot.getValue(comentario::class.java)
                        comentariosArrayList.add(estreno!!)
                    }
                    ComentariosRecyclerview.adapter=ComentariosAdapter(comentariosArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}