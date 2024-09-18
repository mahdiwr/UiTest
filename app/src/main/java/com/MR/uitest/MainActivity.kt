package com.MR.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MR.uitest.ui.theme.UiTestTheme
import kotlinx.coroutines.delay
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Spacer  // Add this import
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import java.io.File
import com.MR.uitest.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


data class Message(val author: String, val body: String, val adress: String) {
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UiTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Main()
                    }
                }
            }
        }
    }
}

@Composable
fun Main() {
    var author by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var adress by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        if (!submitted) {
            TextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Enter your Name : ") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Enter your Text : ") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = adress,
                onValueChange = { adress = it },
                label = { Text("Enter your adress : ") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { submitted = true }) {
                Text("submit")
            }
        } else {
            Pic(Message(author, body, adress))
        }
    }
}



@Composable
fun Pic(msg: Message) {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(R.drawable.baby),
            contentDescription = "Contact profile photo",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = msg.author, style = TextStyle(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Author: ${message.author}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = message.body, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Address: ${message.adress}", fontSize = 14.sp)
    }
}


@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    val sampleMessages = listOf(
        Message("Mahdi", "Hello, Welcome to my first application!", "123 Street"),
        Message("Sara", "Good job!", "456 Avenue"),
        Message("Ali", "Looks great!", "789 Boulevard")
    )
    Conversation(sampleMessages)
}
