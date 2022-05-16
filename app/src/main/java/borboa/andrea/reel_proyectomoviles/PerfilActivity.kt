package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PerfilActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val usuario: String? = getIntent().getStringExtra("usuario")

        var nombreusuarioView: TextView = findViewById(R.id.txtusuario)
        nombreusuarioView.setText(usuario)



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.perfil
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cartelera -> {
                    var intent = Intent(applicationContext, CarteleraActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.inicio -> {
                    var intent = Intent(applicationContext, InicioActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cines -> {
                    var intent = Intent(applicationContext, cines::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.estrenos -> {
                    var intent = Intent(applicationContext, EstrenosActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.perfil -> return@OnNavigationItemSelectedListener true
            }
            false
        })



    }
}