package borboa.andrea.reel_proyectomoviles

import java.util.HashMap

data class peli (var imagen:String?=null,
                 var titulo:String?=null,
                 var categoria:String?=null,
                 var subtitulo:String?=null,
                 var clasificacion:String?=null,
                 var duracion:String?=null,
                 var director:String?=null,
                 var reparto:String?=null,
                 var videoUrl: String?=null,
                 var sinopsis:String?=null,
                 var comentarios:ArrayList<comentario>?=null)