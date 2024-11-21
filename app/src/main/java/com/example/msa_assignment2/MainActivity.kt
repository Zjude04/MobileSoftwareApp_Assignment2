package com.example.msa_assignment2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.msa_assignment2.ui.theme.MSA_Assignment2Theme

class MainActivity : ComponentActivity() {

    // Tag for logging
    private val TAG = "MainActivity"

    // Register the permission request launcher
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Log permission granted
            Log.d(TAG, "Permission granted: Starting SecondActivity")
            startSecondActivity()
        } else {
            // Log permission denied
            Log.w(TAG, "Permission denied: Cannot start SecondActivity")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MSA_Assignment2Theme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display full name and student ID
            Text("Name: Zane Jude", fontSize = 24.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text("Student ID: 1403207", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

            // Explicit Intent Button to start SecondActivity
            Button(onClick = {
                Log.d(TAG, "Explicit intent button clicked: Checking permission")
                checkAndRequestPermission()
            }, modifier = Modifier.padding(bottom = 8.dp)) {
                Text("Start Activity Explicitly")
            }

            // Implicit Intent Button to start SecondActivity
            Button(onClick = {
                val intent = Intent("com.example.msa_assignment2.ACTION_VIEW")
                startActivity(intent)
            }) {
                Text("Start Activity Implicitly")
            }

            // Button to start Image Capture Activity
            Button(onClick = {
                val intent = Intent(this@MainActivity, ImageCaptureActivity::class.java)
                startActivity(intent)
            }) {
                Text("View Image Activity")
            }
        }
    }

    private fun checkAndRequestPermission() {
        val permission = "com.example.msa_assignment2.MSE412"

        // Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission already granted: Starting SecondActivity")
            startSecondActivity()
        } else {
            // Log the permission request
            Log.i(TAG, "Permission not granted: Requesting permission")
            requestPermissionLauncher.launch(permission)
        }
    }

    private fun startSecondActivity() {
        // Log starting the SecondActivity
        Log.d(TAG, "Launching SecondActivity")
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }
}
