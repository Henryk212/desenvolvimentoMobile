package com.example.trabaio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.trabaio.ui.theme.TrabaioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabaioTheme {

                val n = GenerateRandomNumber()
                Render(n)
            }
        }
    }
}

@Composable
fun Render(n : Int) {

    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display clicksToPrize
        Text(
            text = "Prize Countdown: ${n}",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display count
        Text(
            text = "Count: $count",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Increment Button
        Button(
            onClick = { Counter() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
        ) {
            Text(text = "Increment!", color = Color.White)
        }

    }
}

fun GenerateRandomNumber(): Int {
    return  (Math.random() * 50 + 1).toInt()
}

fun Counter(){

}

@Composable
fun GenerateImage(count : Int){

    GenerateRandomNumber()

    if (count < 10) {
        Image(
            painter = painterResource(id = R.drawable.redwallpaper),
            contentDescription = "Red Wallpaper",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.bluewallpaper),
            contentDescription = "Blue Wallpaper",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

}

