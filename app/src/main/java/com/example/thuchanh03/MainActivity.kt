package com.example.thuchanh03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var selectedOp by remember { mutableStateOf("") }

    fun calculate(op: String) {
        selectedOp = op
        val n1 = num1.toDoubleOrNull()
        val n2 = num2.toDoubleOrNull()

        if (n1 == null || n2 == null) {
            resultText = ""
            return
        }

        val res = when (op) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0.0) n1 / n2 else null
            else -> null
        }

        resultText = if (res != null) {
            if (res % 1.0 == 0.0) res.toInt().toString() else res.toString()
        } else {
            if (op == "/" && n2 == 0.0) "Không chia được cho 0" else ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Thực hành 03",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = num1,
            onValueChange = {
                num1 = it
                if (selectedOp.isNotEmpty()) calculate(selectedOp)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { calculate("+") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDB4437)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.size(60.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("+", fontSize = 22.sp, color = Color.White)
            }

            Button(
                onClick = { calculate("-") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE2A03F)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.size(60.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("-", fontSize = 22.sp, color = Color.White)
            }

            Button(
                onClick = { calculate("*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF512DA8)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.size(60.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("*", fontSize = 22.sp, color = Color.White)
            }

            Button(
                onClick = { calculate("/") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E1E1E)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.size(60.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("/", fontSize = 22.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = num2,
            onValueChange = {
                num2 = it
                if (selectedOp.isNotEmpty()) calculate(selectedOp)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = if (resultText.isEmpty()) "Kết quả:" else "Kết quả: $resultText",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorPreview() {
    MaterialTheme {
        CalculatorScreen()
    }
}