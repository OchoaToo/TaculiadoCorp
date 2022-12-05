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

class LoginActivity : AppCompatActivity() {
    private var auth = Firebase.auth
    private lateinit var googleSignInClient: GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            btnIniciarSesion.setOnClickListener() {
                try {
                    loginUser()
                    //checkUser()
                } catch (e: Exception) {
                    Toast.makeText(this, "Campos Vacios", Toast.LENGTH_SHORT).show()
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