package com.example.takeauction

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var mAuth : FirebaseAuth
    private lateinit var loginbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_login, container, false)

        username = view.findViewById(R.id.login_username)
        password = view.findViewById(R.id.login_password)
        mAuth = Firebase.auth

        view.findViewById<Button>(R.id.signup_btn).setOnClickListener {
            var navRegister = activity as IFragmentNavigation
            navRegister.navigateFrag(RegisterFragment(), true)
        }

        loginbtn = view.findViewById(R.id.login_btn);

        view.findViewById<Button>(R.id.login_btn).setOnClickListener {
            validateEmptyForm()
        }
        return view
    }

    private fun firebaseSignin(){
        loginbtn.isEnabled = false
        loginbtn.alpha = 0.5f
        mAuth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener {
                task->
                if (task.isSuccessful){
                    var homeFragment = activity as IFragmentNavigation
                    homeFragment.navigateFrag(HomeFragment(), true)
                }
                else{
                    loginbtn.isEnabled = true
                    loginbtn.alpha = 1.0f
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show();
                }
            }
    }

    private fun validateEmptyForm(){
        when{
            TextUtils.isEmpty(username.text.toString()) -> {
                username.setError("Empty email")
            }
            TextUtils.isEmpty(password.text.toString()) -> {
                password.setError("Empty password")
            }
            username.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() -> {
                if (Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
                    firebaseSignin()
//                    Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show();
                }
                else{
                    username.setError("Invalid email")
                }
            }
        }

    }


}