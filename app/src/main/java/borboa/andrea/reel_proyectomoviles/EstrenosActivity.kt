package borboa.andrea.reel_proyectomoviles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList

class EstrenosActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estrenos)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.estrenos
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cartelera -> {
                    startActivity(
                        Intent(
                            applicationContext, CarteleraActivity::class.java
                        )
                    )
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }


                R.id.cines -> {
                    startActivity(
                        Intent(
                            applicationContext, cines::class.java
                        )
                    )
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.perfil -> {
                    startActivity(
                        Intent(
                            applicationContext, PerfilActivity::class.java
                        )
                    )
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.inicio -> {
                    startActivity(
                        Intent(
                            applicationContext, InicioActivity::class.java
                        )
                    )
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.estrenos -> return@OnNavigationItemSelectedListener true
            }
            false
        })


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)



        val listaEstrenos = Vector<Estrenos>()
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/KfjEumnQroM\" frameborder=\"0\" allowfullscreen></iframe>",
            "En Guerra Con el Abuelo ",
            "At war with my grandpa",
            "S/C",
            "94 min",
            "comedia",
            "Peter es un niño que se ve obligado a abandonar su habitación debido a que su abuelo recientemente viudo se traslada allí. De esta manera, el niño ve como única opción para retomar su cuarto declararle la guerra a su abuelo."
        ))
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/WdapbGVGjo0\" frameborder=\"0\" allowfullscreen></iframe>",
            "El Padre ",
            "The Father",
            "S/C",
            "94 min",
            "Drama",
            "Anthony tiene casi 83 años. Vive solo en su apartamento de Londres y rechaza todos los auxiliares de enfermería que su hija, Anne, intenta imponerle. Pero Anne lo ve como una necesidad inevitable porque ya no va a poder visitarlo todos los días. Ha tomado la decisión de irse a vivir a París con el hombre que acaba de conocer."
        ))
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/iaibc6LI1_g\" frameborder=\"0\" allowfullscreen></iframe>",
            "Judas y el Mesias Negro",
            "Judas and the Black Messiah",
            "S/C",
            "126 min",
            "Drama",
            "A fines de la década de 1960, el delincuente William Bill O'Neal es arrestado en Chicago después de intentar secuestrar un automóvil mientras se hacía pasar por un oficial federal. Se le acerca el agente especial del FBI Roy Mitchell, quien le ofrece que se retiren los cargos de O'Neal si él trabaja encubierto para la oficina."
        ))
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/0zbJyg1VGVw\" frameborder=\"0\" allowfullscreen></iframe>",
            "Relic: Herencia Maldita",
            "Relic(2022)",
            "B",
            "90 min",
            "Drama, Terror",
            "Luego de la misteriosa desaparición de Edna, Kay y su hija Sam viajan a la casa de campo de la abuela para comenzar su búsqueda. Al llegar, se encuentran con indicios de la inestabilidad mental de Edna y, mientras más tiempo pasen en la casa, una serie de eventos sobrenaturales las llevará a explorar el terror más oscuro que habita en sus mentes."
        ))
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/WkMyeI9E8Q8\" frameborder=\"0\" allowfullscreen></iframe>",
            "Hermosa Venganza",
            "Promising Young Woman (2022)",
            "S/C",
            "113 min",
            "Comedia, Drama, Crimen",
            " Cassie tenía un brillante futuro por delante hasta que un acontecimiento inesperado truncó su carrera. Ahora nada en su vida es lo que parece: es inteligente, audaz y vive una doble vida de noche. Cassie tiene la oportunidad de enmendar todo lo que no salió bien en su pasado... vengándose de los culpables."
        ))
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/b-dfaJ1plvk\" frameborder=\"0\" allowfullscreen></iframe>",
            "Mortal Kombat",
            "Mortal Kombat (2021)",
            "S/C",
            "100 min",
            "Accion, Aventura",
            " En el Japón del siglo XVII, los asesinos de Lin Kuei dirigidos por Bi-Han matan a los guerreros del clan ninja rival Shirai Ryu, liderado por Hanzo Hasashi, incluyendo a Harumi (Yukiko Shinohara), su esposa y a su hijo Satoshi. Hanzo mata a los atacantes antes de ser asesinado por Bi-Han, lo que provoca que su alma sea condenada al Inframundo. Raiden, el dios del trueno, llega al lugar y pone a salvo a la hija pequeña de Hanzo, quien ha sobrevivido."
        ))
        listaEstrenos.add(Estrenos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/aHsaqJWiyLM\" frameborder=\"0\" allowfullscreen></iframe>",
            "Nomadland",
            "Nomadland (2020)",
            "S/C",
            "107 min",
            "Drama",
            " En el poblado de Empire, Nevada, Estados Unidos, en 2011, Fern pierde su empleo después del cierre de una fábrica de materiales de construcción, donde trabajó durante años, junto con su esposo, quien recientemente falleció. Fern decide vender la mayoría de sus pertenencias y comprar una furgoneta para vivir y viajar por el país en busca de trabajo, como nómada de hoy en día"
        ))

        val videoAdapter = VideoAdapter(listaEstrenos)
        recyclerView.adapter = videoAdapter

    }

}