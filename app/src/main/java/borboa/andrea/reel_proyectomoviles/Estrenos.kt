package borboa.andrea.reel_proyectomoviles

import android.widget.VideoView

data class Estrenos(
    var videoUrl: String?="",
    val titulo:String="",
    val subtitulo:String="",
    val clasificacion:String="",
    val duracion:String="",
    val categoria:String="",
    val sinopsis:String="")
