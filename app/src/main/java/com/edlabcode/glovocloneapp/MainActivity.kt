package com.edlabcode.glovocloneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.edlabcode.glovocloneapp.ui.navigation.AppNavGraph
import com.edlabcode.glovocloneapp.ui.theme.GlovoCloneAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            GlovoCloneAppTheme {
                AppNavGraph(navController)
            }
        }
    }
}
