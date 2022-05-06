package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import borboa.andrea.reel_proyectomoviles.databinding.ActivityRegistrarseBinding
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class registrarse : AppCompatActivity() {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyectofinal-c6a6d-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val nombreUsuario: EditText = findViewById(R.id.et_nickname)
        val contraseña: EditText = findViewById(R.id.et_password)
        val repetirContraseña: EditText = findViewById(R.id.et_passwordrepit)
        val email: EditText = findViewById(R.id.et_email)
        val btnRegistrarse: Button = findViewById(R.id.btn_register)


        btnRegistrarse.setOnClickListener{
            var nombreUsuarioTxt: String = nombreUsuario.getText().toString()
            var contraseñaTxt:String = contraseña.getText().toString()
            var repetirContraseñaTxt:String = repetirContraseña.getText().toString()
            var emailTxt:String = email.getText().toString()

            if(nombreUsuarioTxt.isEmpty() || contraseñaTxt.isEmpty() || repetirContraseñaTxt.isEmpty() || emailTxt.isEmpty()){
                Toast.makeText(this,"Por favor ingresa todos los datos solicitados.",
                    Toast.LENGTH_SHORT).show()
            }else if(!contraseñaTxt.equals(repetirContraseñaTxt)){
                Toast.makeText(this,"Las contraseñas no coinciden.",
                    Toast.LENGTH_SHORT).show()
            }else{

                databaseReference.child("usuarios").addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChild(nombreUsuarioTxt)){
                            Toast.makeText(applicationContext,"El usuario ya existe.",
                                Toast.LENGTH_SHORT).show()

                        }else{
                            databaseReference.child("usuarios").child(nombreUsuarioTxt).child("contraseña").setValue(contraseñaTxt)
                            databaseReference.child("usuarios").child(nombreUsuarioTxt).child("email").setValue(emailTxt)

                            Toast.makeText(applicationContext,"Usuario registrado correctamente.",
                                Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, iniciar_sesion::class.java))
                        }
                    }
                })


            }
        }

    }
}