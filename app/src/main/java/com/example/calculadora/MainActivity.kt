package com.example.calculadora

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.CalculadoraTheme
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.lang.Exception
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var expressaoTextView: TextView
    private lateinit var resultadoTextView: TextView

    private var expressao = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expressaoTextView = findViewById(R.id.expressao)
        resultadoTextView = findViewById(R.id.resultado)

        // Restaura o estado se disponível
        savedInstanceState?.let {
            expressao = it.getString("expressao") ?: ""
            expressaoTextView.text = expressao
            resultadoTextView.text = it.getString("resultado") ?: ""
        }

        setupButtons()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Salvar a expressão e o resultado
        outState.putString("expressao", expressao)
        outState.putString("resultado", resultadoTextView.text.toString())
    }
    // teste

    private fun setupButtons() {
        val buttonsMap = mapOf(
            R.id.num_0 to "0",
            R.id.num_1 to "1",
            R.id.num_2 to "2",
            R.id.num_3 to "3",
            R.id.num_4 to "4",
            R.id.num_5 to "5",
            R.id.num_6 to "6",
            R.id.num_7 to "7",
            R.id.num_8 to "8",
            R.id.num_9 to "9",
            R.id.ponto to ".",
            R.id.soma to "+",
            R.id.subtra to "-",
            R.id.multplica to "*",
            R.id.divisao to "/",
            R.id.limpar to "C",
            R.id.igual to "="
        )

        for ((buttonId, value) in buttonsMap) {
            findViewById<TextView>(buttonId).setOnClickListener {
                handleButtonClick(value)
            }
        }

        findViewById<ImageView>(R.id.backspace).setOnClickListener {
            if (expressao.isNotEmpty()) {
                expressao = expressao.substring(0, expressao.length - 1)
                expressaoTextView.text = expressao
            }
        }
    }

    private fun handleButtonClick(value: String) {
        when (value) {
            "C" -> {
                expressao = ""
                resultadoTextView.text = ""
            }
            "=" -> {
                calcularResultado()
            }
            else -> {
                expressao += value
            }
        }
        expressaoTextView.text = expressao
    }

    private fun calcularResultado() {
        try {
            val resultado = eval(expressao)
            resultadoTextView.text = resultado.toString()
        } catch (e: Exception) {
            resultadoTextView.text = "Erro"
        }
    }

    private fun eval(expression: String): Double {
        val expr = expression.replace("X", "*")
        return when {
            expr.contains("+") -> {
                val parts = expr.split("+")
                parts[0].toDouble() + parts[1].toDouble()
            }
            expr.contains("-") -> {
                val parts = expr.split("-")
                parts[0].toDouble() - parts[1].toDouble()
            }
            expr.contains("*") -> {
                val parts = expr.split("*")
                parts[0].toDouble() * parts[1].toDouble()
            }
            expr.contains("/") -> {
                val parts = expr.split("/")
                parts[0].toDouble() / parts[1].toDouble()
            }
            else -> {
                expr.toDouble()
            }
        }
    }


    companion object{const val TAG = "prof, José Paulo " + "DEBUG.LIFE.CYCLE"}

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Metodo onStart() foi invocado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Metodo onRestart() foi invocado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Metodo onResume() foi invocado")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Metodo onPause() foi invocado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Metodo onStop() foi invocado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Metodo onDestroy() foi invocado")
    }
}
