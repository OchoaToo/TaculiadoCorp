package com.example.taculiadocorp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.concurrent.Executor
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import kotlin.math.log


class LoginActivity : AppCompatActivity() {
    lateinit var info: String
    private var auth = Firebase.auth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            btnIniciarSesion.setOnClickListener() {
                try {
                    checkDeviceHasBiometric()

                    //checkUser()
                } catch (e: Exception) {
                    Toast.makeText(this, "Campos Vacios", Toast.LENGTH_SHORT).show()
                }
            }



        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Error de Auth: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult,
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Biometricos Aceptados", Toast.LENGTH_SHORT)
                        .show()
                    loginUser()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Auth fallida",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("LogIn Biometrico")
            .setSubtitle("Utilice sus datos biometricos para acceder")
            .setNegativeButtonText("Use su contrasena")
            .build()

btnIniciarSesion.setOnClickListener(){
    biometricPrompt.authenticate(promptInfo)
}


    }

    fun checkDeviceHasBiometric() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("MY_APP_TAG", "La app si puede autenticar mediante Biometricos")
                info = "Si se pueden utilizar biometricos"
                btnIniciarSesion.isEnabled = true
                loginUser()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("MY_APP_TAG", "No contiene biometricos")
                info = "Este dispositivo no cuenta con autenticacion biometrica"
                btnIniciarSesion.isEnabled = false

            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("MY_APP_TAG", "Biometricos no funcionan actualmente")
                info = "Biometricas no disponibles!"
                btnIniciarSesion.isEnabled = false

            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {

                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
                btnIniciarSesion.isEnabled = false

                startActivityForResult(enrollIntent, 100)
            }
        }




    }





    private fun loginUser(){
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                checkUser()
            }else{
                task.exception?.let {
                    Toast.makeText(this,"Credenciales Incorrectas",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun checkUser() {
        val currentUser = auth.currentUser

        if(currentUser!=null){
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("email",currentUser.email)
            startActivity(intent)


          //ESTO SE VE BIEN FEO
            Toast.makeText(this,"Hola! : ${currentUser.email}",Toast.LENGTH_SHORT).show()

            finish()
        }

    }



   /* private fun googleSignIn(){

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnGoogleSignIn.setOnClickListener(){
            signInServices()
        }


    }

    private fun signInServices(){

        val signIntent = googleSignInClient.signInIntent
        launcher.launch(signIntent)

    }


    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
            if(result.resultCode == Activity.RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
                println("LAUNCHER")
            }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>?) {
        if(task!!.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if(account != null){
                updateUI(account)
                println("Handle Results")
            }

        }else{
            Toast.makeText(this,task!!.exception.toString(), Toast.LENGTH_LONG).show()
        }

    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credentials).addOnCompleteListener {
            if(it.isSuccessful){
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("email",account.email)
                intent.putExtra("name",account.displayName)
                startActivity(intent)
                Toast.makeText(this,"Hola! : ${account.displayName}",Toast.LENGTH_LONG).show()

                finish()
            }else{
                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }*/

}