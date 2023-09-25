package com.example.happybirthday

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
                BackgroundImage()
                TextBox()
                TextOverlay()
            }
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

@Composable
fun BackgroundImage() {
    Box {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black))
        Column {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.Transparent))
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = "Background Image",
                modifier = Modifier
                    .weight(5f)
                    .fillMaxSize()
            )
            Box(
                Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .background(Color.Transparent))
        }
    }
}

@Composable
fun TextBox(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        Box(
            Modifier
                .weight(6f)
                .fillMaxWidth()
                .background(Color.Transparent))
        Image(
            painterResource(id = R.drawable.text_box),
            contentDescription = "Text Box",
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(bottom = 10.dp))
        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Transparent))
    }
}

fun textList(tapCount: Int): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currentDate: String = sdf.format(Date())
    val textArray: Array<String> = arrayOf(
        "Hello there!",
        "You're finally awake",
        "Did I disturb you?",
        "Today is a very important day",
        "Why, it's $currentDate of course",
        "Your Birthday",
        "Happy Birthday!"
    )
    return textArray[tapCount]
}

@Composable
fun TextOverlay(modifier: Modifier = Modifier) {
    var words by remember { mutableStateOf("Hello there!") }
    var tapCount by remember { mutableStateOf(0) }
    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (tapCount < 6) {
                            tapCount++
                            tryAwaitRelease()
                        }
                        else {
                            tapCount = 0
                            tryAwaitRelease()
                        }
                    }
                )
            }
    )
    words = textList(tapCount)
    Column(modifier.fillMaxSize()) {
        Box(
            Modifier
                .weight(6f)
                .fillMaxWidth()
                .background(Color.Transparent))
        Column(Modifier.weight(2f)) {
            Box(
                Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )
            Row(Modifier.weight(4f)) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.Transparent)
                )
                Box(
                    Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .background(Color.Transparent)) {
                    Text(
                        text = words,
                        fontFamily = FontFamily(Font(R.font.rockboulder, FontWeight.Normal)),
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.Transparent)
                )
            }
            Box(
                Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )
        }
        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Transparent))
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        BackgroundImage()
        TextBox()
        TextOverlay()
    }
}