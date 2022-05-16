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
    val datosList = ArrayList<datos>()
    lateinit var datosPeli:datos

    //comentarios
    val comentarios1 = ArrayList<comentario>()
    val comentarios2 = ArrayList<comentario>()
    val comentarios3 = ArrayList<comentario>()
    val comentarios4 = ArrayList<comentario>()
    val comentarios5 = ArrayList<comentario>()
    val comentarios6 = ArrayList<comentario>()
    val comentarios7 = ArrayList<comentario>()
    val comentarios8 = ArrayList<comentario>()
    val comentarios9 = ArrayList<comentario>()
    val comentarios10 = ArrayList<comentario>()
    val comentarios11 = ArrayList<comentario>()
    val comentarios12 = ArrayList<comentario>()


    private lateinit var bdref:DatabaseReference
    private lateinit var bdref1:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartelera)
        val usuario: String? = getIntent().getStringExtra("usuario")


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.cartelera
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.inicio -> {
                    var intent = Intent(applicationContext, CarteleraActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.estrenos -> {
                    var intent = Intent(applicationContext, EstrenosActivity::class.java)
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
                R.id.cartelera -> return@OnNavigationItemSelectedListener true
            }
            false
        })

        if (usuario != null) {
            Cargardatos(usuario)
        }


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
                                peliculasAdapter!!.notifyDataSetChanged()

                            }
                        }
                    } else {
                        displayList.clear()
                        displayList.addAll(displayPeliculas)
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

    fun Cargardatos(user:String) {
        bdref= FirebaseDatabase.getInstance().getReference("peliculas1")
        bdref1=FirebaseDatabase.getInstance().getReference("comentarios")
        bdref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (datosSnapshot in dataSnapshot.children){
                    val imagen: String = datosSnapshot.child("imagen").getValue().toString()
                    val titulo: String = datosSnapshot.child("titulo").getValue().toString()
                    val categoria: String = datosSnapshot.child("categoria").getValue().toString()
                    val subtitulo: String = datosSnapshot.child("subtitulo").getValue().toString()
                    val clasificacion: String = datosSnapshot.child("clasificacion").getValue().toString()
                    val duracion: String = datosSnapshot.child("duracion").getValue().toString()
                    val director: String = datosSnapshot.child("director").getValue().toString()
                    val reparto: String = datosSnapshot.child("reparto").getValue().toString()
                    val videoUrl: String = datosSnapshot.child("videoUrl").getValue().toString()
                    val sinopsis: String = datosSnapshot.child("sinopsis").getValue().toString()
                        datosPeli=datos(imagen,titulo,categoria,subtitulo,clasificacion,duracion,director,reparto,videoUrl,sinopsis)
                        if(datosList.contains(datosPeli) == false) {
                            datosList.add(datosPeli)
                        }
                        bdref1.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    for (comentariosSnapshot in snapshot.children) {
                                        if (datosSnapshot.child("idPeli").getValue().toString().equals(comentariosSnapshot.child("idPeli").getValue().toString())==true) {
                                            val nombreUsuario : String= comentariosSnapshot.child("usuario").getValue().toString()
                                            val fecha : String=comentariosSnapshot.child("fecha").getValue().toString()
                                            val comentario : String=comentariosSnapshot.child("comentario").getValue().toString()
                                            val estrellas:Float =(comentariosSnapshot.child("estrellas").getValue().toString()).toFloat()
                                            val idPeli:String= comentariosSnapshot.child("idPeli").getValue().toString()
                                            val coment:comentario=(comentario(nombreUsuario,fecha,comentario,estrellas))
                                            if(idPeli.equals("peli1")){
                                                comentarios1.add(coment)
                                            }else if(idPeli.equals("peli2")){
                                                comentarios2.add(coment)
                                            }else if(idPeli.equals("peli3")){
                                                comentarios3.add(coment)
                                            }else if(idPeli.equals("peli4")){
                                                comentarios4.add(coment)
                                            }else if(idPeli.equals("peli5")) {
                                                comentarios5.add(coment)
                                            }else if(idPeli.equals("peli6")) {
                                                comentarios6.add(coment)
                                            }else if(idPeli.equals("peli7")) {
                                                comentarios7.add(coment)
                                            }else if(idPeli.equals("peli8")) {
                                                comentarios8.add(coment)
                                            }else if(idPeli.equals("peli9")) {
                                                comentarios9.add(coment)
                                            }else if(idPeli.equals("peli10")) {
                                                comentarios10.add(coment)
                                            }else if(idPeli.equals("peli11")) {
                                                comentarios11.add(coment)
                                            }else{
                                                comentarios12.add(coment)
                                            }
                                        }
                                    }

                                }
                            }
                            override fun onCancelled(error: DatabaseError) {} })

                    }
                    for (data in datosList) {
                        val imagen1: String = data.imagen.toString()
                        val titulo1: String = data.titulo.toString()
                        val categoria1: String = data.categoria.toString()
                        val subtitulo1: String = data.subtitulo.toString()
                        val clasificacion1: String = data.clasificacion.toString()
                        val duracion1: String = data.duracion.toString()
                        val director1: String = data.director.toString()
                        val reparto1: String = data.reparto.toString()
                        val videoUrl1: String = data.videoUrl.toString()
                        val sinopsis1: String = data.sinopsis.toString()

                        if(titulo1.equals("Godzila vs Kong")){
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios1))
                        }else if(titulo1.equals("Caos: El Inicio")){
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios2))
                        }else if(titulo1.equals("El Protector")){
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios3))
                        }else if(titulo1.equals("UUUPS! 2 La Aventura Continúa")){
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios4))
                        }else if(titulo1.equals("El Día Del Fin Del Mundo")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios5))
                        }else if(titulo1.equals("El Tunel")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios6))
                        }else if(titulo1.equals("Tom y Jerry")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios7))
                        }else if(titulo1.equals("Pinochio")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios8))
                        }else if(titulo1.equals("Juega Conmigo")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios9))
                        }else if(titulo1.equals("Mujer Maravilla")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios10))
                        }else if(titulo1.equals("El Cazador De Monstruos")) {
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios11))
                        }else{
                            displayPeliculas.add(peli(imagen1, titulo1, categoria1, subtitulo1, clasificacion1, duracion1, director1, reparto1, videoUrl1, sinopsis1, comentarios12))
                        }
                    }
                    displayList.addAll(displayPeliculas)
                    peliculasAdapter = ItemAdapter(this@CarteleraActivity, displayList,user)
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
        var usuario:String

        constructor(contexto: Context, peliculas: ArrayList<peli>, usuario:String) {
            this.contexto = contexto
            this.peliculas = peliculas
            this.usuario = usuario
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
            var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.cartelera_item, null)
            var user = usuario

            var image: ImageView = vista.findViewById(R.id.imagen_peliculacartelera) as ImageView
            var title: TextView = vista.findViewById(R.id.titulo_peliculacartelera) as TextView
            var categoria: TextView = vista.findViewById(R.id.categoria_peliculacartelera) as TextView
            var estrellas: RatingBar = vista.findViewById(R.id.ratingBar) as RatingBar
            var ratinMedia = pelicula.comentarios?.map {
                it.estrellas.toString().toDouble()
            }?.average().toString().toFloat()

            Glide.with(contexto!!).load(pelicula.imagen).into(image);
            title.setText(pelicula.titulo)
            categoria.setText(pelicula.categoria)
            if (ratinMedia != null) {
                estrellas.rating = ratinMedia
            }



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
                bundle.putFloat("promedio",ratinMedia)
                bundle.putString("usuario",user)

                intent.putExtras(bundle);
                contexto!!.startActivity(intent)
            }

            return vista

        }

    }
}



