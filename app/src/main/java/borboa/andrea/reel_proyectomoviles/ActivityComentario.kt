package borboa.andrea.reel_proyectomoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class ActivityComentario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)
        val usuario: String? = getIntent().getStringExtra("usuario")

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
}