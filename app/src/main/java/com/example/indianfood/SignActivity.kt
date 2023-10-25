package com.example.indianfood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.indianfood.databinding.ActivitySignBinding
import com.example.indianfood.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity() {
    private lateinit var username:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClint: GoogleSignInClient
    private val binding:ActivitySignBinding by lazy {
        ActivitySignBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //init Firebase Database
        auth = Firebase.auth
        //Database
        database = Firebase.database.reference

        //Google
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id1)).requestEmail().build()
        googleSignInClint = GoogleSignIn.getClient(this,googleSignInOptions)


        binding.createAccountButton.setOnClickListener {
            username = binding.userName.text.toString()
            email = binding.emailAddress.text.toString().trim()
            password = binding.passsword.text.toString().trim()
            if (email.isEmpty()||password.isBlank()||username.isBlank()){
                Toast.makeText(this,"Please Fill all the details",Toast.LENGTH_SHORT).show()
            }else{
                creatAccount(email,password)
            }

        }
        binding.alreadyhavebutton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
      binding.googleButton.setOnClickListener {
          val signIntent = googleSignInClint.signInIntent
          launcher.launch(signIntent)
      }
    }
    //launcher gor google sign in
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (task.isSuccessful){
                val account: GoogleSignInAccount? = task.result
                val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Sign in successfull",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Sign in faild",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else{
            Toast.makeText(this,"Sign in faild",Toast.LENGTH_SHORT).show()
        }
    }

    private fun creatAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Account Created Fail",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount: Failure",task.exception)
            }
        }
    }

    private fun saveUserData() {
        username = binding.userName.text.toString()
        email = binding.emailAddress.text.toString().trim()
        password = binding.passsword.text.toString().trim()

        val user = UserModel(username,email, password)
        val  userId = FirebaseAuth.getInstance().currentUser!!.uid
        //save data to FireBase
        database.child("user").child(userId).setValue(user)
    }


}