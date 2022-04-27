package borboa.andrea.reel_proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import borboa.andrea.reel_proyectomoviles.databinding.ActivityIniciarSesionBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class iniciar_sesion : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityIniciarSesionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        binding= ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnIndex.setOnClickListener {
            val mEmail=binding.etNick.text.toString()
            val mPassword=binding.etContraseA.text.toString()

            when{
                mEmail.isEmpty() || mPassword.isEmpty()->{
                    Toast.makeText(baseContext, "E-mail o contraseña Incorrectos.",
                        Toast.LENGTH_SHORT).show()
                }else ->{
                    SignIn(mEmail,mPassword)
                }
            }
        }

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this,registrarse::class.java)
            startActivity(intent)
        }


        var txt_recoverypassword: TextView = findViewById(R.id.txt_recoverypassword)
        var txt_recoveryuser: TextView = findViewById(R.id.txt_recoveryuser)



        txt_recoverypassword.setOnClickListener{
            var intent:Intent=Intent(this,PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }

        txt_recoveryuser.setOnClickListener{
            var intent:Intent=Intent(this,RecoveryUserActivity::class.java)
            startActivity(intent)
        }

    }

    private fun SignIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    reload()
                    //val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "E-mail o contraseña incorrectos.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun reload(){
        val intent = Intent(this,InicioActivity::class.java)
        this.startActivity(intent)
    }


}