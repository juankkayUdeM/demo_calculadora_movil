package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTheme {
                Calculadora()
            }
        }
    }
}

@Composable
fun Calculadora() {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("0") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp).background(Color.hsl(136F, 0.28F, 0.76F)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(text="Calculadora", fontSize=50.sp)
        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = numero1,
            onValueChange = { numero1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp).background(Color.White),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = numero2,
            onValueChange = { numero2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp).background(Color.White),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Button(onClick = { resultado = calcular(numero1, numero2, "+") }) { Text("+") }
            Button(onClick = { resultado = calcular(numero1, numero2, "-") }) { Text("-") }
            Button(onClick = { resultado = calcular(numero1, numero2, "*") }) { Text("×") }
            Button(onClick = { resultado = calcular(numero1, numero2, "/") }) { Text("÷") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Resultado: $resultado", fontSize = 20.sp)
    }
}

fun calcular(num1: String, num2: String, operacion: String): String {
    val n1 = num1.toDoubleOrNull()
    val n2 = num2.toDoubleOrNull()

    return if (n1 != null && n2 != null) {
        when (operacion) {
            "+" -> (n1 + n2).toString()
            "-" -> (n1 - n2).toString()
            "*" -> (n1 * n2).toString()
            "/" -> if (n2 != 0.0) (n1 / n2).toString() else "Error: ÷0"
            else -> "Error"
        }
    } else {
        "Error"
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculadora() {
    DemoTheme {
        Calculadora()
    }
}
