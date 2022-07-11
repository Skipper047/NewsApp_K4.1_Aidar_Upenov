package com.example.newsapp_k41_aidar_upenov.ui.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.newsapp_k41_aidar_upenov.databinding.FragmentLoginBinding
import com.example.newsapp_k41_aidar_upenov.kotlinFile.sT
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private lateinit var auth: FirebaseAuth

    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private var timer: CountDownTimer? = null

    var verificationId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth= FirebaseAuth.getInstance()
        var currentUser = auth.currentUser
        if (currentUser!= null){
            findNavController().navigateUp()
        }
        binding.btnContinue.setOnClickListener {
            login()
            binding.editPhone.visibility =View.GONE
            binding.btnContinue.visibility= View.GONE
            binding.editCode.visibility= View.VISIBLE
            binding.btnConfirm.visibility= View.VISIBLE
            binding.textTimer.visibility= View.VISIBLE
            startCountDownTimer(60000)
        }
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                sT("Ошибка!")
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId = p0
            }

        }
        binding.btnConfirm.setOnClickListener {
            val vCode = binding.editCode.text.toString().trim()
            if (vCode.isEmpty()){
                sT("Введите код")
            }else{
                authenticate()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })

        binding.btnExit.setOnClickListener {
            requireActivity().finish()
        }
        binding.btnChangeNumber.setOnClickListener {
            binding.editPhone.visibility =View.VISIBLE
            binding.btnContinue.visibility= View.VISIBLE
            binding.editCode.visibility= View.GONE
            binding.btnConfirm.visibility= View.GONE
            binding.textTimer.visibility= View.GONE
            binding.btnChangeNumber.visibility= View.GONE
        }


    }

    private fun authenticate() {
        val verifNum = binding.editCode.text.toString()
        val credential: PhoneAuthCredential= PhoneAuthProvider.getCredential(verificationId,verifNum)
        signInWithPhoneAuthCredential(credential)
    }

    private fun login() {
        val number = binding.editPhone.text.toString().trim()
        if (number.isNotEmpty()){
            requestSMS(number)
        }else{
            sT("Введите номер телефона")
        }
    }


    private fun requestSMS(number:String) {
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    sT("Успешная авторизация")
                    val user = task.result?.user
//                    findNavController().navigateUp()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        sT("Ошибка при авторизации")
                    }
                    // Update UI
                }
            }
    }
    private fun startCountDownTimer(timeMillis:Long){
        timer?.cancel()
        timer = object  : CountDownTimer(timeMillis,1){
            override fun onTick(p0: Long) {
                val time = p0/1000
                binding.textTimer.text = time.toString()
            }

            override fun onFinish() {
                sT("Время истекло")
                binding.textTimer.text="0"
                binding.textTimer.visibility = View.GONE
                binding.btnChangeNumber.visibility = View.VISIBLE
            }

        }.start()
    }



}