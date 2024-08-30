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
import androidx.compose.foundation.layout.wrapContentSize
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
                var start by remember { mutableStateOf('y') }
                var n by remember { mutableStateOf(GenerateNumber()) }
                var clicks by remember { mutableStateOf(0) }
                if (start == 'y') {
                    Render(clicks, n, start, onClickDecrement = {clicks--},onClickIncrement = {clicks++},onRestart = {
                        n = GenerateNumber()
                        clicks = 0
                        start = 'y'
                    })
                }
            }
        }
    }
}

@Composable
fun Render(clicks : Int, n : Int, start : Char,
           onClickIncrement: () -> Unit,
           onClickDecrement: () -> Unit,
           onRestart: () -> Unit) {

    if (start == 'y') {
        Text(text = "$n",
            color = Color.Black,
            modifier = Modifier.padding(50.dp))

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Count: $clicks", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onClickIncrement() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)) {
                Text(text = "Increment!", color = Color.White)

            }

            Button(onClick = { onClickDecrement() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
            ) {
                Text(text = "Decrement!", color = Color.White)
            }

            if (clicks < (n  * 0.33)) {
                Image(painter = painterResource(id = R.drawable.planta1), contentDescription = "Flower 1",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop)
            }

            if (clicks >= (n * 0.33) && clicks < (n * 0.66)) {
                Image(painter = painterResource(id = R.drawable.planta2), contentDescription = "Flower 2",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop)
            }

            if (clicks >= (n * 0.66) && clicks < (n * 0.99)) {
                Image(painter = painterResource(id = R.drawable.planta3), contentDescription = "Flower 3",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop)
            }

            if (clicks == n) {
                Image(painter = painterResource(id = R.drawable.sun), contentDescription = "Sun",
                    modifier = Modifier,
                    contentScale = ContentScale.Crop)

                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Play again?")
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { onRestart() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                    ) {
                        clicks == 0
                        Text(text = "Yes")

                    }
                }
            }

        }
    }
}

fun GenerateNumber() : Int {
    return (Math.random() * 50 + 1).toInt()
}