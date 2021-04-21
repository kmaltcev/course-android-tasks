package com.simple.ex3a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.simple.ex3a.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import com.simple.ex3a.R.string

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent = Intent(this, Registration::class.java)
    }

    fun getRegistrationActivity(view: View) {
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                binding.greetingView.text = data?.extras?.get("Greets").toString()
                binding.btnReg.text = getString(string.btnAgain)
                binding.btnReg.setOnClickListener { recreate() }
            }
        }
    }
}