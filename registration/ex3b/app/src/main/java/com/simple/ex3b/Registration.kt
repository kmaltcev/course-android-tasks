package com.simple.ex3b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.simple.ex3b.databinding.ActivityRegistrationBinding
import android.os.Bundle
import android.view.View
import android.widget.RadioButton

class Registration : AppCompatActivity() {
    private var rbCheckedText: String = ""
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent = Intent()
    }

    fun setResultAndSend(view: View) {
        with(binding) {
            val greetingStr = getString(if (rbCheckedText == "Male") R.string.m_greeting else R.string.f_greeting)
            intent.putExtra("Greets", "$greetingStr ${firstname.text} ${lastname.text}")
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton && view.isChecked) {
            rbCheckedText = view.text.toString()
        }
    }
}