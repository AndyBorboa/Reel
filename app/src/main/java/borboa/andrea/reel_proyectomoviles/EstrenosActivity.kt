package borboa.andrea.reel_proyectomoviles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class EstrenosActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var bdref:DatabaseReference
    private lateinit var estrenosArrayList: ArrayList<Estrenos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estrenos)
        val usuario: String? = getIntent().getStringExtra("usuario")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.estrenos
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cartelera -> {
                    var intent = Intent(applicationContext, CarteleraActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.inicio -> {
                    var intent = Intent(applicationContext, InicioActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cines -> {
                    var intent = Intent(applicationContext, cines::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.perfil -> {
                    var intent = Intent(applicationContext, PerfilActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.estrenos -> return@OnNavigationItemSelectedListener true
            }
            false
        })


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        estrenosArrayList= arrayListOf<Estrenos>()
        getEstrenosData()

    }
    private fun getEstrenosData(){
        bdref= FirebaseDatabase.getInstance().getReference("estrenos")
        bdref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(estrenosSnapshot in snapshot.children){
                        val estreno = estrenosSnapshot.getValue(Estrenos::class.java)
                        estrenosArrayList.add(estreno!!)
                    }
                    recyclerView.adapter=VideoAdapter(estrenosArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}