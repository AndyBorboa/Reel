package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import borboa.andrea.reel_proyectomoviles.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class registrarse : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistrarseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener{
            val mEmail = binding.etEmail.text.toString()
            val mPassword=binding.etPassword.text.toString()
            val mRepeatPassword=binding.etPasswordrepit.text.toString()
            val mNickname= binding.etNickname.text.toString()

            //Al menos 1 caracter especial
            //Al menos 4 caracteres
            val passwordRegex = Pattern.compile("^" +
            "(?=.*[-@#$%^&+=])" +
            ".{6,}"+
            "$")

            if(mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
                Toast.makeText(this,"Ingrese un e-mail valido,",
                    Toast.LENGTH_SHORT).show()
            }else if (mPassword.isEmpty() || !passwordRegex.matcher(mPassword).matches()){
                Toast.makeText(this,"La contraseña es debil.",
                    Toast.LENGTH_SHORT).show()
            }else if(mPassword!= mRepeatPassword){
                Toast.makeText(this,"Las contraseñas no coinciden.",
                    Toast.LENGTH_SHORT).show()
            }else{
                createAccount(mEmail,mPassword)
                val intent = Intent(this,iniciar_sesion::class.java)
                startActivity(intent)
            }

        }

    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}