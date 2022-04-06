package borboa.andrea.reel_proyectomoviles

import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class PeliculasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)

        val DatosFragment = DatosFragment()
        val bundle = intent.extras

        if (bundle != null) {
            var imagen = bundle.getInt("imagen")
            var titulo = bundle.getString("titulo")
            var subtitulo = bundle.getString("subtitulo")
            var clasificacion = bundle.getString("clasificacion")
            var duracion = bundle.getString("duracion")
            var director = bundle.getString("director")
            var reparto = bundle.getString("reparto")
            var videoUrl = bundle.getString("videoUrl")
            var sinopsis = bundle.getString("sinopsis")
            var categoria = bundle.getString("categoria")



            val args = Bundle()

            args.putInt("imagen", imagen)
            args.putString("titulo", titulo)
            args.putString("subtitulo",subtitulo)
            args.putString("clasificacion",clasificacion)
            args.putString("duracion",duracion)
            args.putString("director", director)
            args.putString("reparto",reparto)
            args.putString("videoUrl",videoUrl)
            args.putString("sinopsis",sinopsis)
            args.putString("categoria",categoria)


            DatosFragment.setArguments(args)


        }

        var viewPager = findViewById(R.id.viewPager) as ViewPager
        var tabLayout = findViewById(R.id.tablayout) as TabLayout

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(DatosFragment,"Datos")
        fragmentAdapter.addFragment(HorariosFragment(),"Horarios")
        fragmentAdapter.addFragment(CalificacionesFragment(),"Calificaciones")




        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
}