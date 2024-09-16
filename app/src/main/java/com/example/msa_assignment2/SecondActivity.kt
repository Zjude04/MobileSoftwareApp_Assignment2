package com.example.msa_assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.msa_assignment2.ui.theme.MSA_Assignment2Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MSA_Assignment2Theme {
                ChallengesScreen()
            }
        }
    }

    @Composable
    fun ChallengesScreen() {
        Column(modifier = Modifier.padding(16.dp)) {
            // List of mobile software engineering challenges
            Text("Mobile Software Engineering Challenges:", fontSize = 24.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text("1. Device fragmentation", fontSize = 18.sp)
            Text("2. Performance optimization", fontSize = 18.sp)
            Text("3. Security vulnerabilities", fontSize = 18.sp)
            Text("4. Battery life management", fontSize = 18.sp)
            Text("5. User experience (UX) consistency", fontSize = 18.sp, modifier = Modifier.padding(bottom = 16.dp))

            // Button to go back to MainActivity
            Button(onClick = {
                val intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
            }) {
                Text("Main Activity")
            }
        }
    }
}
