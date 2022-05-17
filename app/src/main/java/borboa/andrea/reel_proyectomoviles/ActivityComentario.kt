package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class ActivityComentario : AppCompatActivity() {
    private lateinit var comentariosRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)

        comentariosRef= FirebaseDatabase.getInstance().getReference("comentarios")

        val bundle = intent.extras
        var usuario = bundle?.getString("usuario")
        var idPeli = bundle?.getString("idPeli")
        var comentarios = bundle?.getSerializable("comentarios")


        val ratingBar = findViewById<View>(R.id.Rb_comentario) as RatingBar
        val btn_comentar = findViewById<View>(R.id.btn_calificar)
        val msj = findViewById<TextView>(R.id.mensaje) as TextView

        val btn_continuar = findViewById<View>(R.id.btn_continuar)

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date()).toString()

        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, v, b ->
                Toast.makeText(this@ActivityComentario, "Stars: $v", Toast.LENGTH_SHORT).show()
                if (v<=1){
                    msj.setText("¡Mala!")
                }else if(v>1 && v<3){
                    msj.setText("Mediocre")
                }else if(v>2 && v<4){
                    msj.setText("Regular")
                }else if(v>3 && v<5){
                    msj.setText("Buena")
                }else if(v>4){
                    msj.setText("¡Excelente!")
                }
            }

        btn_comentar.setOnClickListener {
            val comentarioPelicula =findViewById(R.id.et_comentario) as TextView
            val estrellasComentario = findViewById(R.id.Rb_comentario) as RatingBar


            var comentarioTxt: String = comentarioPelicula.getText().toString()
            var estrellasTxt:Float = estrellasComentario.rating

            if(comentarioTxt.isEmpty() || estrellasTxt<1){
                Toast.makeText(applicationContext,"Todos los datos son obligatorios!",
                    Toast.LENGTH_SHORT).show()
            }else {
                val coment = comentario(usuario, currentDate, comentarioTxt, estrellasTxt, idPeli)
                comentariosRef.push().setValue(coment)

                Toast.makeText(
                    applicationContext, "Comentario Agregado ",
                    Toast.LENGTH_LONG).show()

                var intent = Intent(applicationContext, CarteleraActivity::class.java)
                intent.putExtra("usuario",usuario)
                startActivity(intent)

            }
        }

        btn_continuar.setOnClickListener{
            var intent = Intent(applicationContext, CarteleraActivity::class.java)
            intent.putExtra("usuario",usuario)
            startActivity(intent)
        }

    }
}