package borboa.andrea.reel_proyectomoviles

import java.io.Serializable

data class comentario (var usuario:String?=null,
                       var fecha:String?=null,
                       var comentario:String?=null,
                       var estrellas:Float?=null,
                       var idPeli:String?=null): Serializable