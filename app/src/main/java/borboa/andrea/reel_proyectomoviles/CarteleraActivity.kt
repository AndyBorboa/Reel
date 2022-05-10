package borboa.andrea.reel_proyectomoviles

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import borboa.andrea.reel_proyectomoviles.databinding.ActivityCarteleraBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class CarteleraActivity : AppCompatActivity() {

    val displayList = ArrayList<peli>()
    var peliculasAdapter: ItemAdapter? = null
    lateinit var gridView_movies: GridView
    var displayPeliculas = ArrayList<peli>()


    private lateinit var reference: DatabaseReference


    lateinit var binding: ActivityCarteleraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartelera)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.cartelera
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {


                R.id.estrenos -> {
                    startActivity(
                        Intent(
                            applicationContext, EstrenosActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cines -> {
                    startActivity(
                        Intent(
                            applicationContext, cines::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.perfil -> {
                    startActivity(
                        Intent(
                            applicationContext, PerfilActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.inicio -> {
                    startActivity(
                        Intent(
                            applicationContext, InicioActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cartelera -> return@OnNavigationItemSelectedListener true
            }
            false
        })

        datos()


        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)

        val search: MenuItem? = menu?.findItem(R.id.searchview)
        val searchView: SearchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.setBackgroundResource(R.drawable.round_button)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        if (search != null) {
            val searchView = search.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val serch = newText.toLowerCase(Locale.getDefault())
                        displayPeliculas.forEach {
                            if (it.titulo?.toLowerCase(Locale.getDefault())
                                    ?.contains(serch) == true
                            ) {
                                displayList.add(it)
                                //peliculasAdapter = ItemAdapter(this@CarteleraActivity,peliculas)
                                peliculasAdapter!!.notifyDataSetChanged()

                            }
                        }
                    } else {
                        displayList.clear()
                        displayList.addAll(displayPeliculas)
                        //peliculasAdapter = ItemAdapter(this@CarteleraActivity,peliculas)
                        peliculasAdapter!!.notifyDataSetChanged()

                    }
                    return true
                }

            })
        }



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }


    fun datos() {
        val rootRef = FirebaseDatabase.getInstance().reference
       rootRef.child("peliculas").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    val imagen:String = dataSnapshot.child("datos").child("imagen").getValue().toString()
                    val titulo:String = dataSnapshot.child("datos").child("titulo").child("titulo").getValue().toString()
                    val categoria:String = dataSnapshot.child("datos").child("categoria").getValue().toString()
                    val subtitulo:String = dataSnapshot.child("datos").child("subtitulo").getValue().toString()
                    val clasificacion:String=dataSnapshot.child("datos").child("clasificacion").getValue().toString()
                    val duracion:String=dataSnapshot.child("datos").child("duracion").getValue().toString()
                    val director:String=dataSnapshot.child("datos").child("director").getValue().toString()
                    val reparto:String=dataSnapshot.child("datos").child("reparto").getValue().toString()
                    val videoUrl:String=dataSnapshot.child("datos").child("videoUrl").getValue().toString()
                    val sinopsis:String=dataSnapshot.child("datos").child("sinopsis").getValue().toString()
                    val comentarios: java.util.ArrayList<comentario> = dataSnapshot.child("comentarios").child("comentarios").getValue() as ArrayList<comentario>

                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))
                    displayList.addAll(displayPeliculas)

                    peliculasAdapter = ItemAdapter(this@CarteleraActivity, displayList)
                    gridView_movies = findViewById(R.id.gridview)
                    gridView_movies.adapter = peliculasAdapter

                }


            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }


    class ItemAdapter : BaseAdapter {
        var peliculas = ArrayList<peli>()
        var contexto: Context? = null

        constructor(contexto: Context, peliculas: ArrayList<peli>) {
            this.contexto = contexto
            this.peliculas = peliculas
        }

        override fun getCount(): Int {
            return this.peliculas.size
        }

        override fun getItem(position: Int): Any {
            return peliculas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var pelicula = peliculas[position]
            var inflator =
                contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.cartelera_item, null)

            var image: ImageView = vista.findViewById(R.id.imagen_peliculacartelera) as ImageView
            var title: TextView = vista.findViewById(R.id.titulo_peliculacartelera) as TextView
            var categoria: TextView =
                vista.findViewById(R.id.categoria_peliculacartelera) as TextView
            var estrellas: RatingBar = vista.findViewById(R.id.ratingBar) as RatingBar

            Glide.with(contexto!!).load(pelicula.imagen).into(image);
            title.setText(pelicula.titulo)
            categoria.setText(pelicula.categoria)



            image.setOnClickListener {
                var intent = Intent(contexto, PeliculasActivity::class.java)
                val bundle = Bundle()

                bundle.putString("imagen", pelicula.imagen)
                bundle.putString("titulo", pelicula.titulo)
                bundle.putString("categoria", pelicula.categoria)
                bundle.putString("subtitulo", pelicula.subtitulo)
                bundle.putString("clasificacion", pelicula.clasificacion)
                bundle.putString("duracion", pelicula.duracion)
                bundle.putString("director", pelicula.director)
                bundle.putString("reparto", pelicula.reparto)
                bundle.putString("videoUrl", pelicula.videoUrl)
                bundle.putString("sinopsis", pelicula.sinopsis)

                bundle.putSerializable("comentarios", pelicula.comentarios)


                intent.putExtras(bundle);
                contexto!!.startActivity(intent)
            }

            return vista

        }

    }
}
