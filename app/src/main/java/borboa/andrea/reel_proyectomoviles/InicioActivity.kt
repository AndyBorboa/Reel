package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import borboa.andrea.reel_proyectomoviles.databinding.ActivityInicioBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class InicioActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityInicioBinding


    private lateinit var CarouselRecyclerview: RecyclerView
    private lateinit var PromosRecyclerview: RecyclerView

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyectofinal-c6a6d-default-rtdb.firebaseio.com/")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val usuario: String? = getIntent().getStringExtra("usuario")


        binding= ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth



        //signOut()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.inicio
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cartelera -> {
                    var intent = Intent(applicationContext, CarteleraActivity::class.java)
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
                R.id.cines -> {
                    var intent = Intent(applicationContext, cines::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.perfil -> {
                    var intent = Intent(applicationContext, PerfilActivity::class.java)
                    intent.putExtra("usuario",usuario)
                    startActivity(intent)

                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.inicio -> return@OnNavigationItemSelectedListener true
            }
            false
        })


        var imagelist: ArrayList<String?>
        var root: StorageReference
        imagelist = ArrayList()
        val listRef = FirebaseStorage.getInstance().reference.child("images")


        listRef.listAll().addOnSuccessListener { listResult ->
            for (file in listResult.items) {
                file.downloadUrl.addOnSuccessListener { uri ->
                    imagelist.add(uri.toString())
                    Log.e("Itemvalue", uri.toString())
                }.addOnSuccessListener {
                    var sliderView: SliderView? = null
                    sliderView= findViewById(R.id.slider)
                    var sliderAdapter: SliderAdapter = SliderAdapter(imagelist)
                    sliderView.setSliderAdapter(sliderAdapter)
                    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
                    sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
                    sliderView.startAutoCycle()
                }
            }
        }



        var promoslist: ArrayList<String?>
        var roots: StorageReference
        promoslist = ArrayList()
        val listRefs = FirebaseStorage.getInstance().reference.child("promos")

        listRefs.listAll().addOnSuccessListener { listResult ->
            for (file in listResult.items) {
                file.downloadUrl.addOnSuccessListener { uri ->
                    promoslist.add(uri.toString())
                    Log.e("Itemvalue", uri.toString())
                }.addOnSuccessListener {
                    PromosRecyclerview = findViewById(R.id.PromosRecyclerview)
                    PromosRecyclerview.setHasFixedSize(true)
                    PromosRecyclerview.setLayoutManager(LinearLayoutManager(this, RecyclerView.VERTICAL, false))
                    val promosAdapter = PromosAdapter(promoslist)
                    PromosRecyclerview.setAdapter(promosAdapter)
                }
            }
        }

    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this,iniciar_sesion::class.java)
        startActivity(intent)
    }
}