package com.example.progress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.progress.ui.theme.ProgressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProgressTheme {
                val n = GenerateNumber()
                Render(n)
            }
        }
    }
}

@Composable
fun Render(n : Int) {
    Text(text = "$n",
        color = Color.Black,
        modifier = Modifier.padding(50.dp))

    var count by remember { mutableStateOf(0) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Count: $count", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)) {
            Text(text = "Increment!", color = Color.White)

        }

        Button(onClick = { count-- },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
        ) {
            Text(text = "Decrement!", color = Color.White)
        }

        if (count < (n  * 0.33)) {
            Image(painter = painterResource(id = R.drawable.planta1), contentDescription = "Flower 1",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop)
        }

        if (count >= (n * 0.33) && count < (n * 0.66)) {
            Image(painter = painterResource(id = R.drawable.planta2), contentDescription = "Flower 2",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop)
        }

        if (count >= (n * 0.66) && count < (n * 0.99)) {
            Image(painter = painterResource(id = R.drawable.planta3), contentDescription = "Flower 3",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop)
        }

        if (count == n) {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription = "Sun",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop)
        }

    }
}

fun GenerateNumber() : Int {
    return (Math.random() * 50 + 1).toInt()
}