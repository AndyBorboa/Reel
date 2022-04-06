package borboa.andrea.reel_proyectomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter internal constructor(private val estrenosList: List<Estrenos>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.estrenos_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoWeb.loadData(estrenosList[position].videoUrl!!, "text/html", "utf-8")
        holder.titulo.setText(estrenosList[position].titulo)
        holder.subtitulo.setText(estrenosList[position].subtitulo)
        holder.clasificacion.setText(estrenosList[position].clasificacion)
        holder.duracion.setText(estrenosList[position].duracion)
        holder.categoria.setText(estrenosList[position].categoria)
        holder.sinopsis.setText(estrenosList[position].sinopsis)
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoWeb: WebView = itemView.findViewById(R.id.webView)
        var titulo : TextView
        val subtitulo : TextView
        val clasificacion : TextView
        val duracion: TextView
        val categoria : TextView
        val sinopsis : TextView

        init {
            titulo = itemView.findViewById(R.id.titulo_estreno)
            subtitulo = itemView.findViewById(R.id.subTitulo)
            clasificacion=itemView.findViewById(R.id.clasificacion)
            duracion=itemView.findViewById(R.id.duracion)
            categoria = itemView.findViewById(R.id.categoria)
            sinopsis = itemView.findViewById(R.id.sinopsis)
            videoWeb.settings.javaScriptEnabled = true
            videoWeb.webChromeClient = object : WebChromeClient() {
            }

        }
    }

    override fun getItemCount(): Int {
        return estrenosList.size

    }
}