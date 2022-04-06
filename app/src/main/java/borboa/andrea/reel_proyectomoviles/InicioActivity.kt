package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class InicioActivity : AppCompatActivity() {
    private lateinit var CarouselRecyclerview: RecyclerView
    private lateinit var PromosRecyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.inicio
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
                R.id.estrenos -> {
                    startActivity(
                        Intent(
                            applicationContext, EstrenosActivity::class.java
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
                R.id.inicio -> return@OnNavigationItemSelectedListener true
            }
            false
        })

        //CarouselRecyclerView
        CarouselRecyclerview = findViewById(R.id.CarouselRecyclerview)
        CarouselRecyclerview.setHasFixedSize(true)
        CarouselRecyclerview.setLayoutManager(LinearLayoutManager(this, RecyclerView.HORIZONTAL, false))


        val imageList: MutableList<CarouselItem> = ArrayList<CarouselItem>()
        imageList.add(CarouselItem(R.drawable.godzilla_vs_kong))
        imageList.add(CarouselItem(R.drawable.liga_de_la_justicia))
        imageList.add(CarouselItem(R.drawable.el_seor_de_los_anillos))
        imageList.add(CarouselItem(R.drawable.pastor_impostor))
        val carouselAdapter = CarouselAdapter(imageList)
        CarouselRecyclerview.setAdapter(carouselAdapter)

        //PromosRecyclerView
        PromosRecyclerview = findViewById(R.id.PromosRecyclerview)
        PromosRecyclerview.setHasFixedSize(true)
        PromosRecyclerview.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))
        val promosList: MutableList<PromosItem> = ArrayList<PromosItem>()
        promosList.add(PromosItem(R.drawable.dosporuno))
        promosList.add(PromosItem(R.drawable.precio_especial_1))
        promosList.add(PromosItem(R.drawable.precio_especial_2))
        promosList.add(PromosItem(R.drawable.precio_especial_3))
        val promosAdapter = PromosAdapter(promosList)
        PromosRecyclerview.setAdapter(promosAdapter)
    }
}