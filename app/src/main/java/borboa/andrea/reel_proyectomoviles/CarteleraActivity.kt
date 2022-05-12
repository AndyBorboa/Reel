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
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList


class CarteleraActivity : AppCompatActivity() {

    val displayList = ArrayList<peli>()
    var peliculasAdapter: ItemAdapter? = null
    lateinit var gridView_movies: GridView
    var displayPeliculas = ArrayList<peli>()
    val comentarios = ArrayList<comentario>()

    private lateinit var bdref:DatabaseReference




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
        bdref= FirebaseDatabase.getInstance().getReference("peliculas1")
        bdref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var imagen:String? =null
                var titulo:String?=null
                var categoria:String? =null
                var subtitulo:String? =null
                var clasificacion:String? =null
                var duracion:String? =null
                var director:String? =null
                var reparto:String?=null
                var videoUrl:String?=null
                var sinopsis:String?=null

                var usuario:String? =null
                var fecha:String?=null
                var comentario:String?=null
                var estrellas:Float?=null

                if(dataSnapshot.exists()){
                    val snapshotpeli : DataSnapshot = dataSnapshot.child("peli1")
                    val snapshotpeli1 : DataSnapshot = dataSnapshot.child("peli2")
                    val snapshotpeli2 : DataSnapshot = dataSnapshot.child("peli3")
                    val snapshotpeli3 : DataSnapshot = dataSnapshot.child("peli4")
                    val snapshotpeli4 : DataSnapshot = dataSnapshot.child("peli5")
                    val snapshotpeli5 : DataSnapshot = dataSnapshot.child("peli6")
                    val snapshotpeli6 : DataSnapshot = dataSnapshot.child("peli7")
                    val snapshotpeli7 : DataSnapshot = dataSnapshot.child("peli8")
                    val snapshotpeli8 : DataSnapshot = dataSnapshot.child("peli9")
                    val snapshotpeli9 : DataSnapshot = dataSnapshot.child("peli10")
                    val snapshotpeli10 : DataSnapshot = dataSnapshot.child("peli11")
                    val snapshotpeli11 : DataSnapshot = dataSnapshot.child("peli12")

                    val snapshotcoments : DataSnapshot = snapshotpeli.child("comentarios/coment1")
                    val snapshotcoments2 : DataSnapshot = snapshotpeli.child("comentarios/coment2")
                    val snapshotcoments3 : DataSnapshot = snapshotpeli.child("comentarios/coment3")
                    val snapshotcoments4 : DataSnapshot = snapshotpeli.child("comentarios/coment4")
                    val snapshotcoments5 : DataSnapshot = snapshotpeli.child("comentarios/coment5")
                    val snapshotcoments6 : DataSnapshot = snapshotpeli.child("comentarios/coment6")
                    val snapshotcoments7 : DataSnapshot = snapshotpeli.child("comentarios/coment7")
                    val snapshotcoments8 : DataSnapshot = snapshotpeli.child("comentarios/coment8")

                        imagen= snapshotpeli.child("imagen").getValue().toString()
                        titulo= snapshotpeli.child("titulo").getValue().toString()
                        categoria= snapshotpeli.child("categoria").getValue().toString()
                        subtitulo= snapshotpeli.child("subtitulo").getValue().toString()
                        clasificacion=snapshotpeli.child("clasificacion").getValue().toString()
                        duracion=snapshotpeli.child("duracion").getValue().toString()
                        director=snapshotpeli.child("director").getValue().toString()
                        reparto=snapshotpeli.child("reparto").getValue().toString()
                        videoUrl=snapshotpeli.child("videoUrl").getValue().toString()
                        sinopsis=snapshotpeli.child("sinopsis").getValue().toString()

                        usuario= snapshotcoments.child("usuario").getValue().toString()
                        fecha= snapshotcoments.child("fecha").getValue().toString()
                        comentario= snapshotcoments.child("comentario").getValue().toString()
                        estrellas=(snapshotcoments.child("estrellas").getValue().toString()).toFloat()

                        comentarios.add(comentario(usuario,fecha,comentario,estrellas))

                        usuario= snapshotcoments2.child("usuario").getValue().toString()
                        fecha= snapshotcoments2.child("fecha").getValue().toString()
                        comentario= snapshotcoments2.child("comentario").getValue().toString()
                        estrellas=(snapshotcoments2.child("estrellas").getValue().toString()).toFloat()
                    comentarios.add(comentario(usuario,fecha,comentario,estrellas))

                    usuario= snapshotcoments3.child("usuario").getValue().toString()
                    fecha= snapshotcoments3.child("fecha").getValue().toString()
                    comentario= snapshotcoments3.child("comentario").getValue().toString()
                    estrellas=(snapshotcoments3.child("estrellas").getValue().toString()).toFloat()
                    comentarios.add(comentario(usuario,fecha,comentario,estrellas))

                    usuario= snapshotcoments4.child("usuario").getValue().toString()
                    fecha= snapshotcoments4.child("fecha").getValue().toString()
                    comentario= snapshotcoments4.child("comentario").getValue().toString()
                    estrellas=(snapshotcoments4.child("estrellas").getValue().toString()).toFloat()
                    comentarios.add(comentario(usuario,fecha,comentario,estrellas))

                    usuario= snapshotcoments5.child("usuario").getValue().toString()
                    fecha= snapshotcoments5.child("fecha").getValue().toString()
                    comentario= snapshotcoments5.child("comentario").getValue().toString()
                    estrellas=(snapshotcoments5.child("estrellas").getValue().toString()).toFloat()
                    comentarios.add(comentario(usuario,fecha,comentario,estrellas))

                    usuario= snapshotcoments6.child("usuario").getValue().toString()
                    fecha= snapshotcoments6.child("fecha").getValue().toString()
                    comentario= snapshotcoments6.child("comentario").getValue().toString()
                    estrellas=(snapshotcoments6.child("estrellas").getValue().toString()).toFloat()
                    comentarios.add(comentario(usuario,fecha,comentario,estrellas))

                    usuario= snapshotcoments7.child("usuario").getValue().toString()
                    fecha= snapshotcoments7.child("fecha").getValue().toString()
                    comentario= snapshotcoments7.child("comentario").getValue().toString()
                    estrellas=(snapshotcoments7.child("estrellas").getValue().toString()).toFloat()
                    comentarios.add(comentario(usuario,fecha,comentario,estrellas))
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))


                        imagen= snapshotpeli1.child("imagen").getValue().toString()
                        titulo= snapshotpeli1.child("titulo").getValue().toString()
                        categoria= snapshotpeli1.child("categoria").getValue().toString()
                        subtitulo= snapshotpeli1.child("subtitulo").getValue().toString()
                        clasificacion=snapshotpeli1.child("clasificacion").getValue().toString()
                        duracion=snapshotpeli1.child("duracion").getValue().toString()
                        director=snapshotpeli1.child("director").getValue().toString()
                        reparto=snapshotpeli1.child("reparto").getValue().toString()
                        videoUrl=snapshotpeli1.child("videoUrl").getValue().toString()
                        sinopsis=snapshotpeli1.child("sinopsis").getValue().toString()

                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))


                    imagen= snapshotpeli2.child("imagen").getValue().toString()
                    titulo= snapshotpeli2.child("titulo").getValue().toString()
                    categoria= snapshotpeli2.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli2.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli2.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli2.child("duracion").getValue().toString()
                    director=snapshotpeli2.child("director").getValue().toString()
                    reparto=snapshotpeli2.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli2.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli2.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli3.child("imagen").getValue().toString()
                    titulo= snapshotpeli3.child("titulo").getValue().toString()
                    categoria= snapshotpeli3.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli3.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli3.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli3.child("duracion").getValue().toString()
                    director=snapshotpeli3.child("director").getValue().toString()
                    reparto=snapshotpeli3.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli3.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli3.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli4.child("imagen").getValue().toString()
                    titulo= snapshotpeli4.child("titulo").getValue().toString()
                    categoria= snapshotpeli4.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli4.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli4.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli4.child("duracion").getValue().toString()
                    director=snapshotpeli4.child("director").getValue().toString()
                    reparto=snapshotpeli4.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli4.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli4.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli5.child("imagen").getValue().toString()
                    titulo= snapshotpeli5.child("titulo").getValue().toString()
                    categoria= snapshotpeli5.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli5.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli5.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli5.child("duracion").getValue().toString()
                    director=snapshotpeli5.child("director").getValue().toString()
                    reparto=snapshotpeli5.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli5.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli5.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli6.child("imagen").getValue().toString()
                    titulo= snapshotpeli6.child("titulo").getValue().toString()
                    categoria= snapshotpeli6.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli6.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli6.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli6.child("duracion").getValue().toString()
                    director=snapshotpeli6.child("director").getValue().toString()
                    reparto=snapshotpeli6.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli6.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli6.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli7.child("imagen").getValue().toString()
                    titulo= snapshotpeli7.child("titulo").getValue().toString()
                    categoria= snapshotpeli7.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli7.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli7.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli7.child("duracion").getValue().toString()
                    director=snapshotpeli7.child("director").getValue().toString()
                    reparto=snapshotpeli7.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli7.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli7.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli8.child("imagen").getValue().toString()
                    titulo= snapshotpeli8.child("titulo").getValue().toString()
                    categoria= snapshotpeli8.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli8.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli8.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli8.child("duracion").getValue().toString()
                    director=snapshotpeli8.child("director").getValue().toString()
                    reparto=snapshotpeli8.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli8.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli8.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli9.child("imagen").getValue().toString()
                    titulo= snapshotpeli9.child("titulo").getValue().toString()
                    categoria= snapshotpeli9.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli9.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli9.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli9.child("duracion").getValue().toString()
                    director=snapshotpeli9.child("director").getValue().toString()
                    reparto=snapshotpeli9.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli9.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli9.child("sinopsis").getValue().toString()
                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli10.child("imagen").getValue().toString()
                    titulo= snapshotpeli10.child("titulo").getValue().toString()
                    categoria= snapshotpeli10.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli10.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli10.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli10.child("duracion").getValue().toString()
                    director=snapshotpeli10.child("director").getValue().toString()
                    reparto=snapshotpeli10.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli10.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli10.child("sinopsis").getValue().toString()

                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))

                    imagen= snapshotpeli11.child("imagen").getValue().toString()
                    titulo= snapshotpeli11.child("titulo").getValue().toString()
                    categoria= snapshotpeli11.child("categoria").getValue().toString()
                    subtitulo= snapshotpeli11.child("subtitulo").getValue().toString()
                    clasificacion=snapshotpeli11.child("clasificacion").getValue().toString()
                    duracion=snapshotpeli11.child("duracion").getValue().toString()
                    director=snapshotpeli11.child("director").getValue().toString()
                    reparto=snapshotpeli11.child("reparto").getValue().toString()
                    videoUrl=snapshotpeli11.child("videoUrl").getValue().toString()
                    sinopsis=snapshotpeli11.child("sinopsis").getValue().toString()

                    displayPeliculas.add(peli(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis,comentarios))
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
