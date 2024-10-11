package com.example.msa_assignment2

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.msa_assignment2.ui.theme.MSA_Assignment2Theme

class ImageCaptureActivity : ComponentActivity() {

    private lateinit var imageCaptureLauncher: ActivityResultLauncher<Intent>
    private var capturedImage by mutableStateOf<Bitmap?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register the activity result launcher
        imageCaptureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Retrieve the bitmap from the result
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                capturedImage = imageBitmap // Update the captured image
            }
        }

        setContent {
            MSA_Assignment2Theme {
                ImageCaptureScreen()
            }
        }
    }

    @Composable
    fun ImageCaptureScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Button to capture image
            Button(onClick = {
                // Launch camera to capture image
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                imageCaptureLauncher.launch(intent) // Use the launcher to start the activity
            }) {
                Text(text = "Capture Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display the captured image if available
            capturedImage?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Captured Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }
    }
}
