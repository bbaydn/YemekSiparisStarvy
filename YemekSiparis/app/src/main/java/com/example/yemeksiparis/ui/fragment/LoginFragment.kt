package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class LoginFragment : Fragment() {
    private lateinit var tasarim: FragmentLoginBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentLoginBinding.inflate(inflater, container, false)

        return tasarim.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseAuth.getInstance().currentUser?.let {
            Navigation.findNavController(view).navigate(R.id.anasayfaGecis)
        }

        tasarim.tvUyeOl.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.kayitGecis)
        }

        tasarim.buttonGirisYap.setOnClickListener {
            if (tasarim.editTextEmailGiris.text.toString()
                    .isNotEmpty() && tasarim.editTextParolaGiris.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    tasarim.editTextEmailGiris.text.toString(),
                    tasarim.editTextParolaGiris.text.toString()
                )
                    .addOnCompleteListener { p0 ->
                        if (p0.isSuccessful) {
                            Snackbar.make(it, "Giriş Başarılı !", Snackbar.LENGTH_SHORT).show()


                            val sayici = object : CountDownTimer(2000,1000){
                                override fun onTick(millisUntilFinished: Long) {}
                                override fun onFinish() {
                                    Navigation.findNavController(it).navigate(R.id.anasayfaGecis)
                                }
                            }
                            sayici.start()
                        } else {
                            Snackbar.make(it, "Hatalı email veya parola", Snackbar.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Snackbar.make(it, "Email ve parola alanları boş bırakılamaz", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
