package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import borboa.andrea.reel_proyectomoviles.databinding.ActivityIniciarSesionBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class iniciar_sesion : AppCompatActivity() {
    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyectofinal-c6a6d-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val nombreUsuario: EditText = findViewById(R.id.et_nick)
        val contraseña: EditText = findViewById(R.id.et_contraseña)
        val btnLogin: Button = findViewById(R.id.btn_index)
        val btnRegister: Button = findViewById(R.id.btn_register)

        btnLogin.setOnClickListener{
            var nombreUsuarioTxt: String = nombreUsuario.getText().toString()
            var contraseñaTxt: String = contraseña.getText().toString()

            if(nombreUsuarioTxt.isEmpty() || contraseñaTxt.isEmpty()){

                Toast.makeText(this,"Por favor ingresa todos los datos solicitados.",
                    Toast.LENGTH_SHORT).show()
            }else{
                databaseReference.child("usuarios").addListenerForSingleValueEvent(object:
                    ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChild(nombreUsuarioTxt)){

                            val getPassword:String = snapshot.child(nombreUsuarioTxt).child("contraseña").getValue().toString()
                            if(getPassword.equals(contraseñaTxt)){
                                Toast.makeText(applicationContext,"Sesion iniciada correctamente.",
                                    Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext, InicioActivity::class.java))

                            }else{
                                Toast.makeText(applicationContext,"Contraseña incorrecta.",
                                    Toast.LENGTH_SHORT).show()
                            }

                        }else{
                            Toast.makeText(applicationContext,"El nombre de usuario no existe.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, registrarse::class.java))

        }


    }


}