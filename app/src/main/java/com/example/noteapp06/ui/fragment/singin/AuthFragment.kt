package com.example.noteapp06.ui.fragment.singin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.credentials.Credential
import androidx.navigation.fragment.findNavController
import com.example.noteapp06.R
import com.example.noteapp06.databinding.FragmentAuthBinding
import com.example.noteapp06.utils.PreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlin.math.sin

class AuthFragment : Fragment() {
    
    private lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleClient: GoogleSignInClient
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account?.idToken)
                }catch (e:ApiException){
                    updateUi(null)
                }
            }
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        
        googleClient = GoogleSignIn.getClient(requireContext(), gso)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(requireContext())
        
        if (auth.currentUser != null || sharedPreferences.onBoard) {
            findNavController().navigate(R.id.action_authFragment_to_noteFragment)
            return
        }
        
        setupListener()
    }
    
    private fun setupListener() {
        binding.btnAuth.setOnClickListener {
            signInLauncher.launch(googleClient.signInIntent)
        }
    }
    
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()){task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    updateUi(user)
                }else{
                    updateUi(null)
                }
            }
    }
    
    private fun updateUi(user: FirebaseUser?) {
        if(user != null){
            findNavController().navigate(R.id.noteFragment)
        }else{
            Toast.makeText(requireContext(), "Аутефикация не удалась ", Toast.LENGTH_LONG).show()
        }
    }
    
    
    
}