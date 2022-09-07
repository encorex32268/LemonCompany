package com.lihan.lemoncompany

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.lihan.lemoncompany.databinding.ActivityMainBinding
import com.lihan.lemoncompany.util.CustomTextWatcher
import com.lihan.lemoncompany.presentation.LoginEvent
import com.lihan.lemoncompany.presentation.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         lifecycleScope.launch {
                viewModel.loginState.collectLatest{
                    binding.apply {
                        textMailErrorMsg.text = it.emailErrorMsg
                        editTextEmail.backgroundTintList = if (it.emailErrorMsg == null){
                            ColorStateList.valueOf(this@MainActivity.getColor(R.color.lightBlue))
                        }else{
                            ColorStateList.valueOf(this@MainActivity.getColor(R.color.orange))
                        }
                        textPasswordErrorMsg.text = it.passwordErrorMsg
                        editTextPassword.backgroundTintList=if (it.passwordErrorMsg == null){
                            ColorStateList.valueOf(this@MainActivity.getColor(R.color.lightBlue))
                        }else{
                            ColorStateList.valueOf(this@MainActivity.getColor(R.color.orange))
                        }
                        textRepasswordErrorMsg.text = it.rePasswordErrorMsg
                        editTextRepassword.backgroundTintList=if (it.rePasswordErrorMsg == null){
                            ColorStateList.valueOf(this@MainActivity.getColor(R.color.lightBlue))
                        }else{
                            ColorStateList.valueOf(this@MainActivity.getColor(R.color.orange))
                        }
                        textAcceptedErrorMsg.text = it.isAcceptedErrorMsg
                    }
            }
        }
        lifecycleScope.launch {
            viewModel.uiEvent.collectLatest {
                AlertDialog.Builder(this@MainActivity,R.style.AlertDialogCustom)
                    .setTitle(R.string.login_dialog_title)
                    .setMessage(R.string.login_dialog_content)
                    .setPositiveButton(R.string.login_dialog_ok,null).show()
            }
        }
        binding.apply {
            editTextEmail.addTextChangedListener(CustomTextWatcher{
                viewModel.onEvent(LoginEvent.EmailInput(it))
            })
            editTextPassword.addTextChangedListener(CustomTextWatcher{
                viewModel.onEvent(LoginEvent.PasswordInput(it))
            })
            editTextRepassword.addTextChangedListener(CustomTextWatcher{
                viewModel.onEvent(LoginEvent.RePasswordInput(it))
            })
            checkBoxAccepted.setOnCheckedChangeListener { _, checkState ->
                viewModel.onEvent(
                    LoginEvent.AcceptedCheck(checkState)
                )
            }
            loginButton.setOnClickListener {
                viewModel.onEvent(LoginEvent.Submit)
            }
        }


    }

}