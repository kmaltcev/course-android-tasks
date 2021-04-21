package com.simple.calculator

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.simple.calculator.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private val tag = "MyActivity"
    private val clicked = R.drawable.clicked_button
    private val unclicked = R.drawable.button_corners
    private var counter = 1
    private var lVal: BigDecimal? = null
    private var rVal: BigDecimal? = null
    private var result: BigDecimal? = null
    private var operator: String? = null
    private var lastClicked: View? = null
    private var toastDBZ: Toast? = null
    private var toastNPE: Toast? = null
    private var toastOPE: Toast? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toastDBZ = Toast.makeText(applicationContext, R.string.dbz, Toast.LENGTH_SHORT)
        toastNPE = Toast.makeText(applicationContext, R.string.npe, Toast.LENGTH_SHORT)
        toastOPE = Toast.makeText(applicationContext, R.string.ope, Toast.LENGTH_SHORT)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            val buttons = arrayOf(btnPlus, btnMinus, btnMultiply, btnDivide)
            setContentView(root)

            operand1.doAfterTextChanged {
                lVal = operand1.text.toString().toBigDecimalOrNull()
                if (operator != null) calculate()
            }
            operand2.doAfterTextChanged {
                rVal = operand2.text.toString().toBigDecimalOrNull()
                if (operator != null) calculate()
            }

            buttons.forEach { btn ->
                btn.setOnClickListener {
                    btnStyler(btn)
                    operator = btn.text.toString()
                    calculate()
                }
            }
        }
    }

    private fun calculate() {
        if (lVal == null || rVal == null)
            toastNPE?.show()
        else {
            when (operator) {
                "+" -> result = lVal?.plus(rVal!!)
                "-" -> result = lVal?.minus(rVal!!)
                "*" -> result = lVal?.multiply(rVal)
                "/" -> {
                    if (rVal == BigDecimal.ZERO)
                        toastDBZ?.show()
                    else
                        result = lVal?.divide(rVal)
                }
                else -> toastOPE?.show()
            }
            binding.tvResult.text = result?.toPlainString()
            result = null
        }
    }

    private fun btnStyler(button: View) {
        lastClicked?.setBackgroundResource(unclicked)
        button.setBackgroundResource(clicked)
        lastClicked = button
    }

    override fun onStart() {
        super.onStart()
        Log.wtf(tag, "onStart $counter")
        counter++
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.wtf(tag, "onSaveInstanceState $counter")
        counter++
    }

    override fun onResume() {
        super.onResume()
        Log.wtf(tag, "onResume $counter")
        counter++
    }

    override fun onPause() {
        super.onPause()
        Log.wtf(tag, "onPause $counter")
        counter++
    }

    override fun onStop() {
        super.onStop()
        Log.wtf(tag, "onStop $counter")
        counter++
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.wtf(tag, "onRestoreInstanceState $counter")
        counter++
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.wtf(tag, "onDestroy $counter")
        counter++
    }
}