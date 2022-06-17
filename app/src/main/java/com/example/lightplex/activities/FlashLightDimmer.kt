package com.example.lightplex.activities

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DoubleArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.lightplex.activities.ui.theme.LightPlexTheme

class FlashLightDimmer : ComponentActivity() {
    var flashlightStatus: Boolean = false
    var periodo: Long = 500
    val mainHandler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        val toggleFlashlight = object: Runnable{
            override fun run() {
                flashlightStatus = !flashlightStatus
                openFlashLight(flashlightStatus,cameraManager,cameraId)
                mainHandler.postDelayed(this, periodo)
            }
        }

        super.onCreate(savedInstanceState)
        setContent {
            LightPlexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        var status = remember { mutableStateOf(true) }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Dimmer()
                        }
                        Spacer(
                            modifier = Modifier.height(75.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(
                                onClick = {
                                    status.value = !status.value
                                    if(!status.value) {
                                        mainHandler.removeCallbacks(toggleFlashlight)
                                        openFlashLight(false,cameraManager,cameraId)
                                    }
                                    else
                                        mainHandler.post(toggleFlashlight)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.secondary
                                )
                            ) {
                                Text(
                                    if (status.value) "OFF" else "ON",
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            }
                        }
                    }
                    mainHandler.post(toggleFlashlight)
                }
            }
        }
    }

    @Composable
    fun Dimmer(){
        val range = 0.0f..100.0f
        val steps = 3
        var selection by remember { mutableStateOf(50f) }
        Icon(
            Icons.Outlined.ChevronRight,
            contentDescription = null
        )
        Slider(
            value = selection,
            valueRange = range,
            steps = steps,
            modifier = Modifier.width(250.dp),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor =  MaterialTheme.colors.secondary,
                inactiveTrackColor = MaterialTheme.colors.primary,
                activeTickColor = MaterialTheme.colors.primaryVariant,
                inactiveTickColor = MaterialTheme.colors.primaryVariant
            ),
            onValueChange = {
                selection = it
            },
            onValueChangeFinished = {
                //periodo = selection.toLong().times(10)
                when(selection){
                    0.0f -> periodo = 2000
                    25.0f -> periodo = 1000
                    50.0f -> periodo = 500
                    75.0f -> periodo = 250
                    100.0f -> periodo = 100
                }
            }
        )
        Icon(
            Icons.Outlined.DoubleArrow,
            contentDescription = null
        )
    }
}

private fun openFlashLight(status: Boolean, cameraManager: CameraManager, cameraId: String) {
    cameraManager.setTorchMode(cameraId, status)
}