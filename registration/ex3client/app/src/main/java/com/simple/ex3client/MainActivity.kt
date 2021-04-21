package com.simple.ex3client

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telecom.TelecomManager
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.simple.ex3client.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var telecomManager: TelecomManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        telecomManager =
            applicationContext.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
    }

    fun makePhoneCall(view: View) {
        val phoneIntent = Intent(Intent.ACTION_DIAL)
        phoneIntent.data = Uri.fromParts("tel", binding.editTextPhone.text.toString(), null)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        startActivity(phoneIntent)
    }

    fun surfWebStie(view: View) {
        val url = binding.editURL.text.toString()

        val urlParsed =
            if (url.contains("https|http".toRegex())) url
            else Uri.fromParts("http", url, null)

        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(webIntent)
    }

    fun sendEmail(view: View) {
        val emailIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.fromParts("mailto", binding.editEmail.text.toString(), null)
        )
        startActivity(emailIntent)
    }

    fun getRegister(view: View) {
        val regIntent = Intent("com.action.register")
        startActivityForResult(regIntent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                binding.editRegister.setText(data?.extras?.get("Greets").toString())
            }
        }
    }
}