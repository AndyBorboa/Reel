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
        reference = rootRef.child("peliculas")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val pelis = snapshot.getValue(peli::class.java)
                    displayPeliculas.add(pelis!!)
                    displayList.addAll(displayPeliculas)


                }
                peliculasAdapter = ItemAdapter(this@CarteleraActivity, displayList)
                gridView_movies = findViewById(R.id.gridview)
                gridView_movies.adapter = peliculasAdapter

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
