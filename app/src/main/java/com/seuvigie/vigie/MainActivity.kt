package com.seuvigie.vigie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.seuvigie.presentation.navigation.AppRoot
import com.seuvigie.vigie.ui.theme.VigieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { VigieTheme { AppRoot() } }
    }
}

