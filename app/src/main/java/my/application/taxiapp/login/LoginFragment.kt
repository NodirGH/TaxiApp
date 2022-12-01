package my.application.taxiapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import my.application.taxiapp.R
import my.application.taxiapp.databinding.FragmentLoginBinding
import my.application.taxiapp.pincode.PinCodeFragment
import my.application.taxiapp.register.RegisterFragment

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvBtnLogin.setOnClickListener {

            val phone = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

            if (phone.isNotEmpty() && password.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(phone, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        navigateToPinCodeFragment()
                    } else Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            } else Toast.makeText(
                requireContext(),
                "Все поля должны быть заполнены",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun navigateToPinCodeFragment() {
        val fragment = PinCodeFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.from_right,
            R.anim.to_left,
            R.anim.from_left,
            R.anim.to_right
        )
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null).commit()
    }

}