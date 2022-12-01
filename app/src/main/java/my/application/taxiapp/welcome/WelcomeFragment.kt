package my.application.taxiapp.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.application.taxiapp.login.LoginFragment
import my.application.taxiapp.R
import my.application.taxiapp.databinding.FragmentWelcomeBinding
import my.application.taxiapp.register.RegisterFragment

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBtnLogin.setOnClickListener{
            navigateToLoginFragment()
        }

        binding.tvBtnRegister.setOnClickListener {
            navigateToRegisterFragment()
        }

    }

    private fun navigateToLoginFragment(){
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

    private fun navigateToRegisterFragment(){
        val fragment = RegisterFragment()
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