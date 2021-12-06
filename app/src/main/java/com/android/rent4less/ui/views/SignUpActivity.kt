package com.android.rent4less.ui.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.rent4less.R
import com.android.rent4less.Utils
import com.android.rent4less.databinding.ActivitySignUpBinding
import com.android.rent4less.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: SignUpViewModel by viewModels()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountButton.setOnClickListener {
            val name = binding.name.text.toString()
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()
            val passwordResult = Utils.validatePassword(password, confirmPassword)
            val detailsResult = Utils.validateDetails(name, userName, passwordResult)

            if (!passwordResult) {
                Toast.makeText(
                    this,
                    getString(R.string.passwords_match_text),
                    Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (!detailsResult) {
                Toast.makeText(
                    this,
                    getString(R.string.username_empty_text),
                    Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (!binding.checkbox.isChecked) {
                Toast.makeText(
                    this,
                    getString(R.string.select_checkbox_text),
                    Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                viewModel.createNewAccount(name, userName, password)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}