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
    private var counter = 1
    private var TAG = "MyActivity"
    private var lVal: BigDecimal? = null
    private var rVal: BigDecimal? = null
    private var result: BigDecimal? = null
    private var operator: String? = null
    private var lastClicked: View? = null
    private var clicked = R.drawable.clicked_button
    private var unclicked = R.drawable.button_corners
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val toastDBZ = Toast.makeText(applicationContext, "Division by zero!", Toast.LENGTH_SHORT)
        val toastNPE = Toast.makeText(
            applicationContext,
            "One or both operands are empty!",
            Toast.LENGTH_SHORT
        )
        val toastOPE = Toast.makeText(
            applicationContext,
            "No operator to perform calculations!",
            Toast.LENGTH_SHORT
        )

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            val buttons = arrayOf(btnPlus, btnMinus, btnMultiply, btnDivide)
            setContentView(root)

            lval.doAfterTextChanged { lVal = lval.text.toString().toBigDecimalOrNull() }
            rval.doAfterTextChanged { rVal = rval.text.toString().toBigDecimalOrNull() }

            buttons.forEach { btn ->
                btn.setOnClickListener {
                    btnStyler(btn)
                    operator = btn.text.toString()
                }
            }

            tvEqual.setOnClickListener {
                btnStyler(tvEqual)
                when {
                    lVal == null || rVal == null -> toastNPE.show()
                    else -> when (operator) {
                        "+" -> result = lVal!!.plus(rVal!!)
                        "-" -> result = lVal!!.minus(rVal!!)
                        "*" -> result = lVal!!.multiply(rVal!!)
                        "/" -> {
                            when (rVal) {
                                BigDecimal.ZERO -> toastDBZ.show()
                                else -> result = lVal?.divide(rVal)
                            }
                        }
                        else -> toastOPE.show()
                    }
                }
                tvResult.text = "$result"
                operator = null
            }
        }
    }

    private fun btnStyler(button: View) {
        lastClicked?.setBackgroundResource(unclicked)
        button.setBackgroundResource(clicked)
        lastClicked = button
    }

    override fun onStart() {
        super.onStart()
        Log.wtf(TAG, "onStart $counter")
        counter++
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.wtf(TAG, "onSaveInstanceState $counter")
        counter++
    }

    override fun onResume() {
        super.onResume()
        Log.wtf(TAG, "onResume $counter")
        counter++
    }

    override fun onPause() {
        super.onPause()
        Log.wtf(TAG, "onPause $counter")
        counter++
    }

    override fun onStop() {
        super.onStop()
        Log.wtf(TAG, "onStop $counter")
        counter++
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.wtf(TAG, "onRestoreInstanceState $counter")
        counter++
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.wtf(TAG, "onDestroy $counter")
        counter++
    }
}