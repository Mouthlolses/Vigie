package com.seuvigie.vigie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.seuvigie.presentation.navigation.AppRoot
import com.seuvigie.vigie.ui.theme.VigieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webClientId = getString(R.string.default_web_client_id)
        enableEdgeToEdge()
        setContent { VigieTheme { AppRoot(webClientId) } }
    }
}

