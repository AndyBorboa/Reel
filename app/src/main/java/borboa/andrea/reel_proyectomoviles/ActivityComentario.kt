package borboa.andrea.reel_proyectomoviles

import java.time.LocalDateTime
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase

class ActivityComentario : AppCompatActivity() {
    private val comentariosRef=FirebaseDatabase.getInstance().getReference("comentarios")
    private val comentarioRef=FirebaseDatabase.getInstance().getReference("comentario")
    private val estrellasRef= FirebaseDatabase.getInstance().getReference("estrellas")
    private val fechaRef=FirebaseDatabase.getInstance().getReference("fecha")
    private val userRef = FirebaseDatabase.getInstance().getReference("Users")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)

        var btnSave: Button =findViewById(R.id.btn_comentario) as Button
        btnSave.setOnClickListener { saveComentariFrom() }

        val ratingBar = findViewById<View>(R.id.rb_comentario) as RatingBar
        val msj = findViewById<TextView>(R.id.mensaje) as TextView
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
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveComentariFrom() {
        var comentario: EditText = findViewById(R.id.et_comentario) as EditText
        var estrellas: RatingBar = findViewById(R.id.rb_comentario) as RatingBar

        val fecha=LocalDateTime.now().toString()

        val usuario = comentario(userRef.database.getReference().toString(),
            fecha,
            comentario.text.toString(),
            estrellas.numStars.toFloat(),
        )
        userRef.push().setValue(usuario)
    }
}