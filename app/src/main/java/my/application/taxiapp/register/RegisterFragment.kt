package my.application.taxiapp.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import my.application.taxiapp.R
import my.application.taxiapp.databinding.FragmentRegisterBinding
import my.application.taxiapp.login.LoginFragment

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvBtnRegister.setOnClickListener {

            val phone = binding.etPhoneNumber.text.toString()
            val name = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()
            val rePassword = binding.etRePassword.text.toString()

            if (phone.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()){
                if (password == rePassword){

                    firebaseAuth.createUserWithEmailAndPassword(phone, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            navigateToLoginFragment()
                        } else Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                    
                } else Toast.makeText(requireContext(), "Пароль не подходит", Toast.LENGTH_SHORT)
                    .show()
            } else Toast.makeText(
                requireContext(),
                "Все поля должны быть заполнены",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun navigateToLoginFragment() {
        val fragment = LoginFragment()
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