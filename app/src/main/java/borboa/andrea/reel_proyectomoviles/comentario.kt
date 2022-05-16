package borboa.andrea.reel_proyectomoviles

import java.io.Serializable

data class comentario (var comentario:String?=null,
                       var estrellas:Float?=null,
                       var fecha:String?=null,
                       var idPeli:String?=null,
                       var nombreUsuario:String?=null): Serializable