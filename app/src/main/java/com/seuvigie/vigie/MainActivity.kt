package com.seuvigie.vigie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.seuvigie.presentation.navigation.AppRoot
import com.seuvigie.presentation.navigation.AppViewModel
import com.seuvigie.vigie.ui.theme.VigieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        val startTime = System.currentTimeMillis()

        splashScreen.setKeepOnScreenCondition {
            val timeout = 1000L // 1 segundo de segurança
            val isTimeout = System.currentTimeMillis() - startTime > timeout

            viewModel.isLogged == null && !isTimeout
        }

        val webClientId = getString(R.string.default_web_client_id)
        enableEdgeToEdge()
        setContent {
            VigieTheme {
                val isLogged = viewModel.isLogged
                if (isLogged != null) {
                    AppRoot(
                        webClientId,
                        isLogged = isLogged
                    )
                }
            }
        }
    }
}

