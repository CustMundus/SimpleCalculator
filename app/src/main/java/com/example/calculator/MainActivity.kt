package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private  enum class OPERATOR {
        PLUS, SUBTRACT, MULTIPLY, DIVIDE, EQUAL
    }

    //InV
    private  var currentNumber: String? = null
    private var  currentOperator: OPERATOR? = null
    private var stringNumberAtRight: String? = null
    private var stringNumberAtLeft: String? = null
    private var calculationResult: Double = 0.0
    private var calculationsString: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.isEnabled = false
        editText.inputType = InputType.TYPE_NULL

        currentNumber = ""
        calculationsString = ""
    }


    fun buttonClicked(view: View){
        when(view.id){

            R.id.btn0 -> numberIsTapped(0)
            R.id.btn1 -> numberIsTapped(1)
            R.id.btn2 -> numberIsTapped(2)
            R.id.btn3 -> numberIsTapped(3)
            R.id.btn4 -> numberIsTapped(4)
            R.id.btn5 -> numberIsTapped(5)
            R.id.btn6 -> numberIsTapped(6)
            R.id.btn7 -> numberIsTapped(7)
            R.id.btn8 -> numberIsTapped(8)
            R.id.btn9 -> numberIsTapped(9)

            R.id.btnSub -> {
                operationIsTapped(OPERATOR.SUBTRACT)
                calculationsString += " - "
            }
            R.id.btnAdd -> {
                operationIsTapped(OPERATOR.PLUS)
                calculationsString += " + "
            }
            R.id.btnDiv -> {
                operationIsTapped(OPERATOR.DIVIDE)
                calculationsString += " / "
            }
            R.id.btnMt -> {
                operationIsTapped(OPERATOR.MULTIPLY)
                calculationsString += " * "
            }
            R.id.btnEq -> {
                operationIsTapped(OPERATOR.EQUAL)
            }
            R.id.btnPr -> implementPercent()
            R.id.btnClear -> implementClear()
        }
        txtView.setText(calculationsString)

    }

    private fun numberIsTapped(tappedNumber: Int) {
        currentNumber += tappedNumber.toString()
        editText.setText(currentNumber)
        calculationsString = currentNumber
        txtView.text = calculationsString
    }

    private fun operationIsTapped(tappedOperation: OPERATOR){
        if (currentOperator != null){

            if(currentNumber != "") {
                stringNumberAtRight= currentNumber
                currentNumber = ""

                when(currentOperator) {
                    OPERATOR.PLUS -> calculationResult = stringNumberAtLeft!!.toDouble() + stringNumberAtRight!!.toDouble()
                    OPERATOR.SUBTRACT -> calculationResult = stringNumberAtLeft!!.toDouble() - stringNumberAtRight!!.toDouble()
                    OPERATOR.DIVIDE -> calculationResult = stringNumberAtLeft!!.toDouble() / stringNumberAtRight!!.toDouble()
                    OPERATOR.MULTIPLY -> calculationResult = stringNumberAtLeft!!.toDouble() * stringNumberAtRight!!.toDouble()
                }
                stringNumberAtLeft = calculationResult.toString()
                editText.setText(calculationResult.toString())
                calculationsString = stringNumberAtLeft

            }
        }else{
            stringNumberAtLeft= currentNumber
            currentNumber = ""
        }
        currentOperator = tappedOperation
    }

    private fun implementPercent(){
        val percentValue = editText.text.toString().toDouble() / 100
        currentNumber = percentValue.toString()
        editText.setText(currentNumber)
    }
    private fun implementClear(){
        stringNumberAtRight = ""
        stringNumberAtLeft = ""
        calculationResult = 0.0
        currentNumber = ""
        currentOperator = null
        editText.setText("0")
        calculationsString = "0" +
                ""
    }
}
