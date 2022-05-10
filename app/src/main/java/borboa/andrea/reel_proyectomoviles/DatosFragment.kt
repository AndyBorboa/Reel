package borboa.andrea.reel_proyectomoviles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DatosFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View?{
                // Inflate the layout for this fragment

                val imagen = getArguments()?.getString("imagen")
                val titulo = getArguments()?.getString("titulo")
                val subtitulo = getArguments()?.getString("subtitulo")
                val clasificacion = getArguments()?.getString("clasificacion")
                val duracion = getArguments()?.getString("duracion")
                val director = getArguments()?.getString("director")
                val reparto = getArguments()?.getString("reparto")
                val videoUrl = getArguments()?.getString("videoUrl")
                val sinopsis = getArguments()?.getString("sinopsis")
                val categoria = getArguments()?.getString("categoria")


                val view: View = inflater.inflate(R.layout.fragment_datos, container, false)

                var videoWeb: WebView = view.findViewById(R.id.videoView)
                videoWeb.settings.javaScriptEnabled = true
                videoWeb.webChromeClient = object : WebChromeClient() {
                }
                videoWeb.loadData(videoUrl!!, "text/html", "utf-8")

                var imagenView: ImageView = view.findViewById(R.id.imagenView)
                var tituloView: TextView = view.findViewById(R.id.tituloView)
                var clasificacionView: TextView = view.findViewById(R.id.clasificacionView)
                var duracionView: TextView = view.findViewById(R.id.duracionView)
                var directorView: TextView = view.findViewById(R.id.directorView)
                var repartoView: TextView = view.findViewById(R.id.repartoView)
                var sinopsisView: TextView = view.findViewById(R.id.sinopsisView)
                var categoriaView: TextView = view.findViewById(R.id.categoriaView)
                var subtituloView: TextView = view.findViewById(R.id.subtituloView)


                Glide.with(this).load(imagen).into(imagenView);
                tituloView.setText(titulo)
                clasificacionView.setText(clasificacion)
                duracionView.setText(duracion)
                directorView.setText(director)
                repartoView.setText(reparto)
                sinopsisView.setText(sinopsis)
                categoriaView.setText(categoria)
                subtituloView.setText(subtitulo)

                return view
        }
}