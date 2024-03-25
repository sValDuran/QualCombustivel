package com.example.qualcombustivel

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var gasolineEditText: EditText
    private lateinit var ethanolEditText: EditText
    private lateinit var mButton: Button
    private lateinit var mTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findById()
        setClick()
    }

        override fun onClick(v: View){
            if (v == mButton){
                calculate()
            }
        }

    private fun calculate(){
        if (gasolineEditText.text.toString().isEmpty() || ethanolEditText.text.toString().isEmpty()) {

            Toast.makeText(this, getString(R.string.indicar), Toast.LENGTH_SHORT)
                .show();
            mTextView.text = ""

        }else{
            val gas = retrieveValue(gasolineEditText)
            val etha = retrieveValue(ethanolEditText)

            val result = etha / gas

            if (result < 0.7) {
                mTextView.text = getString(R.string.answer_ethanol)
                mTextView.setTextColor(resources.getColor(R.color.ethanol, this.theme))
            } else{
                mTextView.text = getString((R.string.answer_gas))
                mTextView.setTextColor(resources.getColor(R.color.gasoline, this.theme))
            }
        }
    }

    private fun retrieveValue(input: EditText): Double{
        return try{
            input.text.toString().toDouble()
        } catch (e:NumberFormatException){
            Toast.makeText(this, getString(R.string.invalid_value), Toast.LENGTH_SHORT).show()
            0.0
        }
    }

    private fun findById(){
        gasolineEditText = findViewById(R.id.edittext_gasoline)
        ethanolEditText= findViewById(R.id.edittext_ethanol)
        mButton = findViewById(R.id.button_calculate)
        mTextView = findViewById(R.id.textview_response)
    }

    private fun setClick(){
        mButton.setOnClickListener(this)
    }


}


