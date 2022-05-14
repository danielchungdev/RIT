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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword : EditText
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.username_text_reg)
        password = view.findViewById(R.id.password_text_reg)
        confirmPassword = view.findViewById(R.id.password_text_reg_conf)

        mAuth = Firebase.auth

        view.findViewById<Button>(R.id.login_btn_reg).setOnClickListener {
            var navRegister = activity as IFragmentNavigation
            navRegister.navigateFrag(LoginFragment(), true)
        }

        view.findViewById<Button>(R.id.register_btn).setOnClickListener {
            validateEmptyForm()
        }
        return view
    }

    private fun firebaseSignup(){
        mAuth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    var homeFragment = activity as IFragmentNavigation
                    homeFragment.navigateFrag(HomeFragment(), true)
                }
                else{
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show();
                }
            }
    }

    private fun validateEmptyForm(){
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Please enter email")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Please enter password")
            }
            TextUtils.isEmpty(confirmPassword.text.toString().trim())->{
                password.setError("Please enter password confirmation")
            }

            username.text.toString().isNotEmpty()
                    && password.text.toString().isNotEmpty()
                    && confirmPassword.text.toString().isNotEmpty() -> {
                        if(password.text.toString().length >= 5){
                            if (Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
                                if(password.text.toString() == confirmPassword.text.toString()){

                                    firebaseSignup()
                                }
                                else{
                                    confirmPassword.setError("Passwords don't match!")
                                }
                            }
                            else{
                                username.setError("Invalid Email")
                            }
                        }
                        else{
                            password.setError("Password has to be longer than 5.")
                        }
            }
        }
    }
}