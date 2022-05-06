package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import borboa.andrea.reel_proyectomoviles.databinding.ActivityInicioBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList

class InicioActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityInicioBinding


    private lateinit var CarouselRecyclerview: RecyclerView
    private lateinit var PromosRecyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        //signOut()

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


        var sliderView: SliderView? = null
        var images = intArrayOf(
            R.drawable.godzilla_vs_kong,
            R.drawable.liga_de_la_justicia,
            R.drawable.el_seor_de_los_anillos,
            R.drawable.pastor_impostor
        )

        sliderView= findViewById(R.id.slider)
        var sliderAdapter: SliderAdapter = SliderAdapter(images)
        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView.startAutoCycle()



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

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this,iniciar_sesion::class.java)
        startActivity(intent)
    }
}