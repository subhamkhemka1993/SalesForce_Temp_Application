package com.example.mysalesforcetempapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mysalesforcetempapplication.ui.theme.MySalesForceTempApplicationTheme
import com.example.mysalesforcetempapplication.viewmodel.AppViewModel
import com.salesforce.android.smi.ui.UIClient
import java.util.logging.Level
import java.util.logging.Logger

class MainActivity : ComponentActivity() {

    //    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()
    var uiClient: UIClient? = null
    private val logger = Logger.getLogger(TAG)

    companion object {
        val TAG: String = MainActivity::class.java.name
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.resetMessagingConfig()
        showMessagingUI()
        enableEdgeToEdge()
        setContent {
            MySalesForceTempApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun showMessagingUI() {
        // Create a UI client
        if (viewModel.uiConfig != null) {
            uiClient = UIClient.Factory.create(viewModel.uiConfig!!)
        }

        // Show the UI
        if (uiClient != null) {
            uiClient!!.openConversationActivity(this)
        } else {
            logger.log(Level.INFO, "Unable to create a conversation for messaging.")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MySalesForceTempApplicationTheme {
        Greeting("Android")
    }
}

