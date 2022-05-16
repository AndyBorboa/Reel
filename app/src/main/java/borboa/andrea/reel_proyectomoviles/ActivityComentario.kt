package borboa.andrea.reel_proyectomoviles

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ActivityComentario : AppCompatActivity() {
    private val userRef = FirebaseDatabase.getInstance().getReference("comentarios")
    private val idPeli = FirebaseDatabase.getInstance().getReference("idPeli")
    private val nombreUsuario= FirebaseDatabase.getInstance().getReference("usuarios")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)
        val usuario: String? = getIntent().getStringExtra("usuario")

        var btnSave: Button =findViewById(R.id.btn_comentar) as Button
        btnSave.setOnClickListener { saveComentariFrom() }

        val ratingBar = findViewById<View>(R.id.Rb_comentario) as RatingBar
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
        var estrellas: RatingBar = findViewById(R.id.Rb_comentario) as RatingBar

        val fecha=LocalDateTime.now()
        val formatter =DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formated = fecha.format(formatter)




        val usuario = comentario(comentario.text.toString(),
            estrellas.rating,
            formated.toString(),
            idPeli.key.toString(),
            nombreUsuario.child("usuario").toString()
        )
        userRef.push().setValue(usuario)
        Toast.makeText(applicationContext,"Comentario Agregado ",
            Toast.LENGTH_SHORT).show()
        return
    }
}